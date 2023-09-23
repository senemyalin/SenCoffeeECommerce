package com.senemyalin.sencoffee.presentation.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.senemyalin.sencoffee.R
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.common.toastMessage
import com.senemyalin.sencoffee.common.viewBinding
import com.senemyalin.sencoffee.databinding.FragmentHomeBinding
import com.senemyalin.sencoffee.databinding.FragmentSearchBinding
import com.senemyalin.sencoffee.presentation.home.HomeAdapter
import com.senemyalin.sencoffee.presentation.home.HomeFragmentDirections
import com.senemyalin.sencoffee.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding(FragmentSearchBinding::bind)
    private val searchProductAdapter = HomeAdapter(::onProductClick, ::onAddClick)
    private val viewModel by viewModels<SearchViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvSearchProducts.adapter = searchProductAdapter
        initSearchBar()
        observeData()
    }

    private fun initSearchBar() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if ((newText?.length ?: 0) >= 3) {
                    viewModel.searchProducts(newText.orEmpty())
                }
                return true
            }
        })
    }

    private fun observeData() {
        viewModel.searchProduct.observe(viewLifecycleOwner) { products ->
            when (products) {
                is NetworkResponse.Error -> Unit
                NetworkResponse.Loading -> Unit
                is NetworkResponse.Success -> {
                    products.result?.let { searchProductAdapter.updateList(it) }
                }

                null -> Log.e("searchProduct", "Search products are null")
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
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(id.toString()))
    }

    private fun onAddClick(productId: Int) {
        val user = Firebase.auth.currentUser
        val getUid = user?.uid

        viewModel.addToCart(productId, getUid!!)
    }
}