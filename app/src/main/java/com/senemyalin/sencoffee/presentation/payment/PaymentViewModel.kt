package com.senemyalin.sencoffee.presentation.payment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.sencoffee.common.NetworkResponse
import com.senemyalin.sencoffee.domain.usecase.remote.clearcart.ClearCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {

    private var _deleteCartProductsResponse = MutableLiveData<NetworkResponse<Int>?>()
    val deleteCartProductsResponse: LiveData<NetworkResponse<Int>?>
        get() = _deleteCartProductsResponse

    fun clearCart(userId: String) {
        viewModelScope.launch {
            clearCartUseCase(userId).collect {
                when (it) {
                    is NetworkResponse.Error -> {
                        _deleteCartProductsResponse.postValue(it)
                    }

                    NetworkResponse.Loading -> Unit
                    is NetworkResponse.Success -> {
                        _deleteCartProductsResponse.postValue(it)
                    }
                }
            }
        }
    }
}