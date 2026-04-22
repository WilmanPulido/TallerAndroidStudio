package com.example.helloandroidpulidowilman.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloandroidpulidowilman.data.task.Task
import com.example.helloandroidpulidowilman.data.task.TaskRepository
import com.example.helloandroidpulidowilman.databinding.FragmentTaskListBinding

class TaskListFragment : Fragment() {

    private var _binding: FragmentTaskListBinding? = null
    private val binding get() = _binding!!

    private lateinit var repository: TaskRepository
    private lateinit var adapter: TaskAdapter
    private val taskList = mutableListOf<Task>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTaskListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = TaskRepository(requireContext())

        setupRecyclerView()
        setupClickListeners()

// Cargar tareas guardadas
        val tasks = repository.getAllTasks()
        adapter.submitList(tasks)
    }

    private fun setupRecyclerView() {
        adapter = TaskAdapter { task ->

            val action = TaskListFragmentDirections
                .actionTaskListFragmentToTaskDetailFragment(task.id)

            findNavController().navigate(action)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        adapter.submitList(taskList)
    }

    private fun setupClickListeners() {

        binding.btnAddTask.setOnClickListener {
            findNavController().navigate(
                TaskListFragmentDirections.actionTaskListFragmentToTaskDetailFragment()
            )
        }
    }

    override fun onResume() {
        super.onResume()

        val tasks = repository.getAllTasks()
        adapter.submitList(tasks)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}