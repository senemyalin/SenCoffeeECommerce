package com.senemyalin.sencoffee.presentation.favourites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.senemyalin.sencoffee.R
import com.senemyalin.sencoffee.common.viewBinding
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavouritesFragment : Fragment(R.layout.fragment_favourites) {

    private val binding by viewBinding(FragmentFavouritesBinding::bind)
    private val viewModel by viewModels<FavouritesViewModel>()

    private val favouritesAdapter = FavouritesAdapter(::onProductClick, ::onDeleteClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvFavouriteProducts.adapter = favouritesAdapter
        getFavouriteProducts()
        setListener()
        observeData()
    }

    private fun setListener() {
        binding.btnLogout.setOnClickListener {
            viewModel.deleteAllFavouriteProducts()
            Firebase.auth.signOut()
            findNavController().navigate(FavouritesFragmentDirections.actionFavouritesFragmentToLoginFragment())
        }
    }

    private fun getFavouriteProducts() {
        viewModel.getFavouriteProducts()
    }

    private fun observeData() {
        viewModel.favouriteProducts.observe(viewLifecycleOwner) { products ->
            if (products != null) {
                favouritesAdapter.updateList(products)
            }
        }
    }

    private fun onProductClick(productId: Int) {
        findNavController().navigate(
            FavouritesFragmentDirections.actionFavouritesFragmentToDetailFragment(
                productId.toString()
            )
        )
    }

    private fun onDeleteClick(product: Product) {
        viewModel.deleteFromFavourites(product)
        viewModel.getFavouriteProducts()
    }

}