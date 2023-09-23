package com.senemyalin.sencoffee.presentation.payment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.senemyalin.sencoffee.R
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.common.toastMessage
import com.senemyalin.sencoffee.common.viewBinding
import com.senemyalin.sencoffee.databinding.FragmentPaymentBinding
import com.senemyalin.sencoffee.presentation.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment(R.layout.fragment_payment) {
    private val binding by viewBinding(FragmentPaymentBinding::bind)
    private val viewModel by viewModels<PaymentViewModel>()

    private val args: PaymentFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
        observeData()
    }

    private fun observeData() {
        viewModel.deleteCartProductsResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Error -> Unit
                NetworkResponse.Loading -> Unit
                is NetworkResponse.Success -> {
                    if(response.result == 200){
                        handleResponse()
                    }
                }
                null -> Log.e("clearCartProducts", "clearCartProducts response is null")
            }
        }
    }

    private fun handleResponse() {
        findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToHomeFragment())
    }

    private fun setData() {
        val user = Firebase.auth.currentUser
        val userId = user?.uid

        with(binding) {
            tvTotalPrice.text = "Total Price: ${args.totalprice} ₺"
            btnPay.setOnClickListener {
                if (!etAddress.text.isNullOrEmpty() &&
                    !etCvc.text.isNullOrEmpty() &&
                    !etCardHolderName.text.isNullOrEmpty() &&
                    !etExpirationMonth.text.isNullOrEmpty() &&
                    !etExpirationYear.text.isNullOrEmpty() &&
                    !etCreditCardNumber.text.isNullOrEmpty()
                ) {
                    if (userId != null) {
                        viewModel.clearCart(userId)
                    }
                } else {
                    requireContext().toastMessage("Fill in the blanks, please!")
                }
            }
        }
    }
}