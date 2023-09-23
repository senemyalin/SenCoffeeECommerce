package com.senemyalin.sencoffee.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.AddToCartRequest
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.usecase.remote.addtocart.AddToCartUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.searchproduct.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchProductUseCase: SearchProductUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private var _searchProduct = MutableLiveData<NetworkResponse<List<Product>>?>()
    val searchProduct: LiveData<NetworkResponse<List<Product>>?>
        get() = _searchProduct

    private var _addToCartResponse = MutableLiveData<NetworkResponse<Int>?>()
    val addToCartResponse: LiveData<NetworkResponse<Int>?>
        get() = _addToCartResponse


    fun searchProducts(searchKey: String) {
        viewModelScope.launch {
            searchProductUseCase(searchKey).collect {
                when (it) {
                    is NetworkResponse.Error -> {
                        _searchProduct.postValue(it)
                    }

                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> {
                        _searchProduct.postValue(it)
                    }
                }
            }
        }
    }

    fun addToCart(productId: Int, userId: String) {
        viewModelScope.launch {
            addToCartUseCase(
                AddToCartRequest(
                    productId,
                    userId
                )
            ).collect {
                when(it){
                    is NetworkResponse.Error -> Unit
                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> _addToCartResponse.postValue(it)
                }
            }
        }
    }
}