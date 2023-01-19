package com.example.taskapp51.ui.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taskapp51.databinding.FragmentOnBoardPageBinding

class OnBoardPageFragment : Fragment() {

    lateinit var binding: FragmentOnBoardPageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPageBinding.inflate(inflater,container,false)

        initViews()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
    }

    private fun initViews() {
        arguments.let {
            val data = it?.getSerializable("OnBoard") as BoardModel
            binding.imgBoard.setImageResource(data.img)
            binding.tvToDo.text = data.title
            binding.tvDescription.text = data.description
        }
    }
}