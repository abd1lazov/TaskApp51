package com.example.taskapp51.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.taskapp51.databinding.FragmentProfileBinding
import com.example.taskapp51.ui.utils.Preferences

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val prefs: Preferences? = null


    var mGetContent = registerForActivityResult(ActivityResultContracts.GetContent()){ uri ->
        binding.imgProfile.setImageURI(uri)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()
        initListeners()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initListeners() {

        binding.imgProfile.setOnClickListener{
            mGetContent.launch("image/*")
        }
    }

    private fun initViews() {

    }
}