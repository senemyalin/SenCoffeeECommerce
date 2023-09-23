package com.senemyalin.sencoffee.presentation.login


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
import com.senemyalin.sencoffee.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        if (auth.currentUser != null) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        with(binding) {
            tvRegister.click {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }

            btnLogin.click {
                val getEmail = etEmail.text.toString()
                val getPassword = etPassword.text.toString()

                if (getEmail.isNotEmpty() && getPassword.isNotEmpty()) login(getEmail, getPassword)
                else requireContext().toastMessage("Please try again?")
            }
        }
    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            requireContext().toastMessage("Sign in successfully!")
        }.addOnFailureListener {
            requireContext().toastMessage(it.message.orEmpty())
        }
    }
}