package com.example.helloandroidpulidowilman.ui

import androidx.navigation.fragment.navArgs
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.helloandroidpulidowilman.ui.task.receiver.TaskReminderReceiver
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.helloandroidpulidowilman.databinding.FragmentTaskDetailBinding
import com.example.helloandroidpulidowilman.data.task.Task
import com.example.helloandroidpulidowilman.data.task.TaskRepository

class TaskDetailFragment : Fragment() {

    private val args: TaskDetailFragmentArgs by navArgs()

    private var _binding: FragmentTaskDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: TaskRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository = TaskRepository(requireContext())

        setupClickListeners()

        val taskId = args.taskId

        // 🔹 Modo edición
        if (taskId != -1) {
            val task = repository.getAllTasks().find { it.id == taskId }

            task?.let {
                binding.etTitle.setText(it.title)
                binding.etDescription.setText(it.description)
                binding.etDate.setText(it.date)
                binding.cbReminder.isChecked = it.hasReminder
            }
        }
    }

    private fun setupClickListeners() {
        binding.btnSave.setOnClickListener {

            val context = requireContext()
            val taskId = args.taskId

            val task = Task(
                id = if (taskId == -1) {
                    System.currentTimeMillis().toInt()
                } else {
                    taskId
                },
                title = binding.etTitle.text.toString(),
                description = binding.etDescription.text.toString(),
                date = binding.etDate.text.toString(),
                hasReminder = binding.cbReminder.isChecked
            )

            // 🔹 Crear o actualizar
            if (taskId == -1) {
                repository.addTask(task)
            } else {
                repository.updateTask(task)
            }

            // 🔔 Recordatorio
            if (task.hasReminder) {

                val intent = Intent(context, TaskReminderReceiver::class.java)

                val pendingIntent = PendingIntent.getBroadcast(
                    context,
                    task.id,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )

                val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

                val triggerTime = System.currentTimeMillis() + 10000

                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }

            findNavController().navigateUp()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}