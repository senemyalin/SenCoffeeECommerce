package com.senemyalin.sencoffee.presentation.cart

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.senemyalin.sencoffee.R
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.common.viewBinding
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CartFragment : Fragment(R.layout.fragment_cart) {

    private val binding by viewBinding(FragmentCartBinding::bind)
    private val viewModel by viewModels<CartViewModel>()
    private val cartAdapter = CartAdapter(::onProductClick, ::onDeleteClick)

    private var userUid: String? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val user = Firebase.auth.currentUser
        userUid = user?.uid

        binding.rvFavouriteProducts.adapter = cartAdapter

        userUid?.let {
            getCartProducts(it)
        }

        observeData()

    }

    private fun getCartProducts(id: String) {
        viewModel.getCartProducts(id)
    }

    private fun observeData() {
        viewModel.cartProducts.observe(viewLifecycleOwner) { products ->
            when (products) {
                is NetworkResponse.Error -> Unit
                NetworkResponse.Loading -> Unit
                is NetworkResponse.Success -> {
                    products.result?.let {
                        handleData(it)
                    }
                }

                null -> Log.e("getCartProducts", "CartProducts are null")
            }

        }
    }

    private fun handleData(cartProducts: List<Product>) {
        var totalPrice = 0.0

        cartProducts.forEach {
            if (it.saleState) {
                totalPrice += it.salePrice
            } else {
                totalPrice += it.price
            }
        }
        binding.tvPrice.text = "Total Price: $totalPrice ₺"

        cartAdapter.updateList(cartProducts)
    }

    private fun onDeleteClick(productId: Int) {
        viewModel.deleteCartProducts(productId.toString())

        userUid?.let {
            getCartProducts(it)
        }
    }

    private fun onProductClick(productId: Int) {
        findNavController().navigate(
            CartFragmentDirections.actionCartFragmentToDetailFragment(
                productId.toString()
            )
        )
    }
}