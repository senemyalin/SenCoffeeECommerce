package com.senemyalin.sencoffee.presentation.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.senemyalin.sencoffee.data.dto.Product
import com.senemyalin.sencoffee.domain.usecase.local.deleteallfromfavourite.DeleteAllFromFavouriteUseCase
import com.senemyalin.sencoffee.domain.usecase.local.deletefromfavourite.DeleteFromFavouriteUseCase
import com.senemyalin.sencoffee.domain.usecase.local.getallfavouriteproducts.GetAllFavouriteProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getAllFavouriteProductsUseCase: GetAllFavouriteProductsUseCase,
    private val deleteFromFavouriteUseCase: DeleteFromFavouriteUseCase,
    private val deleteAllFromFavouriteUseCase: DeleteAllFromFavouriteUseCase
) : ViewModel() {


    private var _favouriteProducts = MutableLiveData<List<Product>?>()
    val favouriteProducts: LiveData<List<Product>?>
        get() = _favouriteProducts

    fun getFavouriteProducts() {
        viewModelScope.launch {
            getAllFavouriteProductsUseCase().collect {
                _favouriteProducts.postValue(it)
            }
        }
    }

    fun deleteAllFavouriteProducts() {
        viewModelScope.launch {
            deleteAllFromFavouriteUseCase()
        }
    }


    fun deleteFromFavourites(product: Product) {
        viewModelScope.launch {
            deleteFromFavouriteUseCase(product)
        }
    }
}
