package com.example.taskapp51.ui.home.new_task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taskapp51.App
import com.example.taskapp51.databinding.FragmentNewTaskBinding
import com.example.taskapp51.ui.home.TaskModel

class NewTaskFragment : Fragment() {

    private lateinit var binding: FragmentNewTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTaskBinding.inflate(inflater, container, false)

        initViews()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.btnSave.setOnClickListener {

            App.database.taskDao()?.insert(
                TaskModel(
                    title = binding.etTitle.text.toString().trim(),
                    desc = binding.etDesc.text.toString()
                )
            )
            findNavController().navigateUp()
        }
    }

    private fun initViews() {

    }

}