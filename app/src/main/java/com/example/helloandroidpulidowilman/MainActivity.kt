package com.example.helloandroidpulidowilman

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {
    // ViewModel a nivel de Activity (compartido con fragments)
    private val viewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // El Fragment se carga automáticamente desde el XML
        // El ViewModel está disponible para todos los fragments
    }
}