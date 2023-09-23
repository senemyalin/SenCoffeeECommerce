package com.senemyalin.sencoffee.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.data.dto.DeleteFromCartRequest
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.usecase.remote.deletefromcart.DeleteFromCartUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.getcartproducts.GetCartProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCartProductsUseCase: GetCartProductsUseCase,
    private val deleteFromCartUseCase: DeleteFromCartUseCase
) : ViewModel() {


    private var _cartProducts = MutableLiveData<NetworkResponse<List<Product>>?>()
    val cartProducts: LiveData<NetworkResponse<List<Product>>?>
        get() = _cartProducts

    private var _deleteCartProductResponse = MutableLiveData<NetworkResponse<Int>?>()
    val deleteCartProductResponse: LiveData<NetworkResponse<Int>?>
        get() = _deleteCartProductResponse

    fun getCartProducts(userId: String) {
        viewModelScope.launch {
            getCartProductsUseCase(userId).collect {
                when (it) {
                    is NetworkResponse.Error -> {
                        _cartProducts.postValue(it)
                    }

                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> {
                        _cartProducts.postValue(it)
                    }
                }
            }
        }
    }

    fun deleteCartProducts(productId: String) {
        viewModelScope.launch {
            deleteFromCartUseCase(
                DeleteFromCartRequest(
                    productId.toInt()
                )
            ).collect {
                when (it) {
                    is NetworkResponse.Error -> {
                        _deleteCartProductResponse.postValue(it)
                    }

                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> {
                        _deleteCartProductResponse.postValue(it)
                    }
                }
            }
        }
    }
}
