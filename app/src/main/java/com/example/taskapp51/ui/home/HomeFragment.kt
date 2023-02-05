package com.example.taskapp51.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp51.App
import com.example.taskapp51.R
import com.example.taskapp51.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        setHasOptionsMenu(true)

        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu )
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.sort_menu){

            val items = arrayOf("Date", "A-z", "z-A")

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Sort by:")
            builder.setItems(items){ dialog, which ->
                when(which){
                     0->{
                        adapter.addTasks(App.database.taskDao()?.getAllTaskByDate() as List<TaskModel>)
                         dialog.dismiss()
                     }
                    1->{
                        adapter.addTasks(App.database.taskDao()?.getAllTaskByAlphabetAz() as List<TaskModel>)
                        dialog.dismiss()
                    }
                    2->{
                        adapter.addTasks(App.database.taskDao()?.getAllTaskByAlphabetZa() as List<TaskModel>)
                        dialog.dismiss()
                    }
                }
            }
            builder.show()
        }
        return super.onOptionsItemSelected(item)
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

        setData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = TaskAdapter(this:: onLongClickListener)
    }

    private fun onLongClickListener(pos: Int){

        val builder = MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.Delete))
            .setMessage(resources.getString(R.string.Message))
            .setNegativeButton(resources.getString(R.string.No)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.Yes)) { _, _ ->
                App.database.taskDao()?.delete(adapter.getTask(pos))
                setData()
            }
        builder.show()
    }

    private fun setData(){
        val listOfTasks = App.database.taskDao()?.getAllTasks()
        adapter.addTasks(listOfTasks as List<TaskModel>)
    }
}