package com.senemyalin.sencoffee.presentation.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.senemyalin.sencoffee.R
import com.senemyalin.sencoffee.common.click
import com.senemyalin.sencoffee.common.toastMessage
import com.senemyalin.sencoffee.common.viewBinding
import com.senemyalin.sencoffee.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val binding by viewBinding(FragmentRegisterBinding::bind)
    private lateinit var auth : FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        if(auth.currentUser != null){
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
        }

        with(binding){
            btnRegister.click {
                val getEmail = etEmail.text.toString()
                val getPassword = etPassword.text.toString()

                if (getEmail.isNotEmpty() && getPassword.isNotEmpty()) {
                    register(getEmail,getPassword)
                }
                else requireContext().toastMessage("Try again.")
            }

            tvLogin.click {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
    }

    private fun register(email:String, password:String){
        auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
            findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
            requireContext().toastMessage("Sign up successfully!")
        }.addOnFailureListener {
            requireContext().toastMessage(it.message.orEmpty())
        }
    }
}