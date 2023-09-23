package com.senemyalin.sencoffee.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.AddToCartRequest
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.usecase.local.addtofavourite.AddToFavouriteUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.addtocart.AddToCartUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.getproductdetailsbyid.GetProductDetailsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getProductDetailsByIdUseCase: GetProductDetailsByIdUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase
) : ViewModel() {

    private var _productDetails = MutableLiveData<NetworkResponse<Product>?>()
    val productDetails: LiveData<NetworkResponse<Product>?>
        get() = _productDetails

    private var _addToCartResponse = MutableLiveData<NetworkResponse<Int>?>()
    val addToCartResponse: LiveData<NetworkResponse<Int>?>
        get() = _addToCartResponse

    fun getProductDetails(id: String) {
        viewModelScope.launch {
            getProductDetailsByIdUseCase(id).collect {
                when (it) {
                    is NetworkResponse.Error -> {
                        _productDetails.postValue(it)
                    }
                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> {
                        _productDetails.postValue(it)
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

    fun addToFavourites(product: Product) {
        viewModelScope.launch {
            addToFavouriteUseCase(product)
        }
    }
}