package com.example.taskapp51.utils.onBoard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.taskapp51.R
import com.example.taskapp51.databinding.FragmentOnBoardPageBinding
import com.example.taskapp51.utils.Preferences

class OnBoardPageFragment(
                var listenerNext: () -> Unit,
                var listenerSkip: () -> Unit):
                Fragment() {

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
        binding.btnStart.setOnClickListener{
            Preferences(requireContext()).setBoardingShowed(true)
            findNavController().navigate(R.id.navigation_home)
//            findNavController().navigateUp()
        }
        binding.btnNext.setOnClickListener{
            listenerNext.invoke()
        }
        binding.btnSkip.setOnClickListener{
            listenerSkip.invoke()
        }
    }

    private fun initViews() {
        arguments.let {
            val data = it?.getSerializable("onBoarding") as BoardModel
            binding.imgBoard.setImageResource(data.img)
            binding.tvToDo.text = data.title
            binding.tvDescription.text = data.description

            binding.btnSkip.isVisible = data.isLast == false
            binding.btnNext.isVisible = data.isLast == false
            binding.btnStart.isVisible = data.isLast == true

            if (!data.isLast){
                binding.boardConst.setBackgroundResource(data.bg)
            }else{
                binding.boardConst.setBackgroundResource(data.bg)
            }
        }
    }
}