package com.senemyalin.sencoffee.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.senemyalin.sencoffee.R
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.common.toastMessage
import com.senemyalin.sencoffee.common.viewBinding
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.databinding.FragmentHomeBinding
import com.senemyalin.sencoffee.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val allProductAdapter = HomeAdapter(::onProductClick, ::onAddClick)
    private val saleProductAdapter = HomeAdapter(::onProductClick, ::onAddClick)

    private val viewModel by viewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAllProducts.adapter = allProductAdapter
        binding.rvSaleProducts.adapter = saleProductAdapter
        viewModel.getAllProducts()
        viewModel.getSaleProducts()
        observeData()
    }

    private fun observeData() {
        viewModel.allProducts.observe(viewLifecycleOwner) { products ->
            when (products) {
                is NetworkResponse.Error -> Unit
                NetworkResponse.Loading -> Unit
                is NetworkResponse.Success -> {
                    products.result?.let { allProductAdapter.updateList(it) }
                }

                null -> Log.e("getAllProducts", "AllProducts are null")
            }

        }

        viewModel.allSaleProducts.observe(viewLifecycleOwner) { saleProducts ->
            when (saleProducts) {
                is NetworkResponse.Error -> Unit
                NetworkResponse.Loading -> Unit
                is NetworkResponse.Success -> {
                    saleProducts.result?.let { saleProductAdapter.updateList(it) }
                }

                null -> Log.e("getAllSaleProducts", "AllSaleProducts are null")
            }
        }

        viewModel.addToCartResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResponse.Error -> Unit
                NetworkResponse.Loading -> Unit
                is NetworkResponse.Success -> {
                    if(response.result == 200){
                        Log.e("AddToCart", "Add to cart is success!")
                        requireContext().toastMessage("Add to cart is success!")
                    }
                }

                null -> Log.e("AddToCart", "Add to cart is failed")
            }
        }
    }

    private fun onProductClick(id: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(id.toString()))
    }

    private fun onAddClick(productId: Int) {
        val user = Firebase.auth.currentUser
        val getUid = user?.uid

        viewModel.addToCart(productId, getUid!!)

    }
}