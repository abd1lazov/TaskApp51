package com.example.taskapp51.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp51.R
import com.example.taskapp51.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()
        initListeners()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initListeners() {
        binding.fabHome.setOnClickListener{
            findNavController().navigate(R.id.newTaskFragment)
        }
    }

    private fun initViews() {

        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = adapter

        setFragmentResultListener("new_task"){key, bundle ->
            val title = bundle.getString("title")
            val desc = bundle.getString("desc")
            Log.e("ololo", "initViews: $title$desc")

            adapter.addTask(TaskModel(title, desc))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter()
    }

}