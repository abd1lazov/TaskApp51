package com.example.taskapp51.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp51.App
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
//            val simpleCallBack = object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
//                override fun onMove(
//                    recyclerView: RecyclerView,
//                    viewHolder: RecyclerView.ViewHolder,
//                    target: RecyclerView.ViewHolder
//                ): Boolean {
//                    return false
//                }
//
//                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                    val position = viewHolder.adapterPosition
//                    items.remove(items[position])
//                    adapter.notifyDataSetChanged()
//                }
//            }
    }

    private fun initViews() {

        binding.rvHome.layoutManager = LinearLayoutManager(context)
        binding.rvHome.adapter = adapter

        val listOfTasks = App.database.taskDao()?.getAllTasks()
        adapter.addTasks(listOfTasks as List<TaskModel>)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter()
    }
}