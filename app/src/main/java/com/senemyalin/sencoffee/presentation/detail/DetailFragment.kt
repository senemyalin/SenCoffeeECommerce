package com.senemyalin.sencoffee.presentation.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.senemyalin.sencoffee.R
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.common.loadImage
import com.senemyalin.sencoffee.common.toastMessage
import com.senemyalin.sencoffee.common.viewBinding
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel by viewModels<DetailViewModel>()

    private val args: DetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProductDetails(args.id)
        observeData()
    }

    private fun observeData() {
        viewModel.productDetails.observe(viewLifecycleOwner) { products ->
            when (products) {
                is NetworkResponse.Error -> Unit
                NetworkResponse.Loading -> Unit
                is NetworkResponse.Success -> handleResponse(products.result)
                null -> Log.e("productDetails", "ProductDetails are null")
            }
        }

        viewModel.addToCartResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Error -> Unit
                NetworkResponse.Loading -> Unit
                is NetworkResponse.Success -> {
                    if (response.result == 200) {
                        Log.e("AddToCart", "Add to cart is success!")
                        requireContext().toastMessage("Add to cart is success!")
                    }
                }

                null -> Log.e("AddToCart", "Add to cart is failed")
            }
        }
    }

    private fun handleResponse(result: Product?) {
        with(binding) {
            imageView.loadImage(result?.imageOne)
            textViewTitle.text = result?.title
            textViewDescription.text = result?.description

            if (result?.saleState == true) {
                textViewPrice.text = "${result.salePrice} ₺ \n Old Price: ${result.price}"
            } else {
                textViewPrice.text = "${result?.price} ₺"
            }

            btnAdd.setOnClickListener {
                onAddClick(args.id.toInt())
            }

        }
    }

    private fun onAddClick(productId: Int) {
        val user = Firebase.auth.currentUser
        val getUid = user?.uid

        viewModel.addToCart(productId, getUid!!)

    }
}


