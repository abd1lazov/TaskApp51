package com.example.taskapp51.ui.authentication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.taskapp51.databinding.FragmentAuthBinding
import com.example.taskapp51.extensions.showToast
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private var auth = FirebaseAuth.getInstance()
    private var correctCode: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(inflater, container, false)

        initViews()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        binding.btnSendPhone.setOnClickListener {
            if (binding.etPhone.text!!.isNotEmpty()) {
                sendPhone()
                showToast("Sending...")
            } else {
                binding.etPhone.error = "Write your phone number!!!"
            }
        }
        binding.btnConfirm.setOnClickListener {

        }
    }

    private fun initViews() {

    }

    private fun sendPhone() {

        auth.setLanguageCode("ru")
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(binding.etPhone.text.toString())
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
//                    showToast(p0.smsCode.toString())
                }

                override fun onVerificationFailed(exception: FirebaseException) {
                    showToast(exception.message.toString())
                }

                override fun onCodeSent(
                    verificationCode: String,
                    p1: PhoneAuthProvider.ForceResendingToken
                ) {
                    super.onCodeSent(verificationCode, p1)
                    correctCode = verificationCode

                    binding.etPhone.isVisible = false
                    binding.btnSendPhone.isVisible = false

                    binding.etCodeLayout.isVisible = true
                    binding.btnConfirm.isVisible = true

                }
            })
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun sendCode() {
        val credential = PhoneAuthProvider.getCredential(
            correctCode.toString(),
            binding.etCode.text.toString()
        )
    }

}