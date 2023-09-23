package com.senemyalin.sencoffee.presentation.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.AddToCartRequest
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.usecase.remote.addtocart.AddToCartUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.getallproducts.GetAllProductsUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.getsaleproducts.GetSaleProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase,
    private val getSaleProductsUseCase: GetSaleProductsUseCase,
    private val addToCartUseCase: AddToCartUseCase
) : ViewModel() {

    private var _allProducts = MutableLiveData<NetworkResponse<List<Product>>?>()
    val allProducts: LiveData<NetworkResponse<List<Product>>?>
        get() = _allProducts

    private var _allSaleProducts = MutableLiveData<NetworkResponse<List<Product>>?>()
    val allSaleProducts: LiveData<NetworkResponse<List<Product>>?>
        get() = _allSaleProducts

    private var _addToCartResponse = MutableLiveData<NetworkResponse<Int>?>()
    val addToCartResponse: LiveData<NetworkResponse<Int>?>
        get() = _addToCartResponse

    fun getAllProducts() {
        viewModelScope.launch {
            getAllProductsUseCase().collect {
                when (it) {
                    is NetworkResponse.Error -> {
                        _allProducts.postValue(it)
                    }

                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> {
                        _allProducts.postValue(it)
                    }
                }
            }
        }
    }

    fun getSaleProducts() {
        viewModelScope.launch {
            getSaleProductsUseCase().collect {
                when (it) {
                    is NetworkResponse.Error -> {
                        _allSaleProducts.postValue(it)
                    }

                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> {
                        _allSaleProducts.postValue(it)
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