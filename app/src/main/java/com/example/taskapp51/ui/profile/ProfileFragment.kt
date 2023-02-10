package com.example.taskapp51.ui.profile

import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.example.taskapp51.R
import com.example.taskapp51.databinding.FragmentProfileBinding
import com.example.taskapp51.extensions.loadImage
import com.example.taskapp51.extensions.showToast
import com.example.taskapp51.utils.Preferences

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private var mGetContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            binding.imgProfile.setImageURI(uri)

            binding.imgProfile.loadImage(uri.toString())

            Preferences(requireContext()).setImageProfile(uri.toString())
            showToast(" Success!")
        }

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.settings, menu)
        super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()
        initListeners()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun initListeners() {

        binding.imgProfile.setOnClickListener {
            mGetContent.launch("image/*")
        }
    }

    private fun initViews() {
        binding.imgProfile.setImageURI(Preferences(requireContext()).getImageProfile()?.toUri())
    }
}