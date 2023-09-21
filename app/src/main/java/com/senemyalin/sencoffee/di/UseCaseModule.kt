package com.senemyalin.sencoffee.di

import com.senemyalin.sencoffee.domain.usecase.local.addtofavourite.AddToFavouriteUseCase
import com.senemyalin.sencoffee.domain.usecase.local.addtofavourite.AddToFavouriteUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.local.deleteallfromfavourite.DeleteAllFromFavouriteUseCase
import com.senemyalin.sencoffee.domain.usecase.local.deleteallfromfavourite.DeleteAllFromFavouriteUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.local.deletefromfavourite.DeleteFromFavouriteUseCase
import com.senemyalin.sencoffee.domain.usecase.local.deletefromfavourite.DeleteFromFavouriteUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.local.getallfavouriteproducts.GetAllFavouriteProductsUseCase
import com.senemyalin.sencoffee.domain.usecase.local.getallfavouriteproducts.GetAllFavouriteProductsUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.remote.addtocart.AddToCartUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.addtocart.AddToCartUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.remote.clearcart.ClearCartUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.clearcart.ClearCartUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.remote.deletefromcart.DeleteFromCartUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.deletefromcart.DeleteFromCartUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.remote.getallproducts.GetAllProductsUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.getallproducts.GetAllProductsUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.remote.getcartproducts.GetCartProductsUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.getcartproducts.GetCartProductsUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.remote.getproductdetailsbyid.GetProductDetailsByIdUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.getproductdetailsbyid.GetProductDetailsByIdUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.remote.getsaleproducts.GetSaleProductsUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.getsaleproducts.GetSaleProductsUseCaseImpl
import com.senemyalin.sencoffee.domain.usecase.remote.searchproduct.SearchProductUseCase
import com.senemyalin.sencoffee.domain.usecase.remote.searchproduct.SearchProductUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    //Remote
    @Binds
    @ViewModelScoped
    abstract fun bindAddToCart(addToCartUseCaseImpl: AddToCartUseCaseImpl): AddToCartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindAClearCart(clearCartUseCaseImpl: ClearCartUseCaseImpl): ClearCartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteFromCart(deleteFromCartUseCaseImpl: DeleteFromCartUseCaseImpl): DeleteFromCartUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllProducts(getAllProductsUseCaseImpl: GetAllProductsUseCaseImpl): GetAllProductsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetCartProducts(getCartProductsUseCaseImpl: GetCartProductsUseCaseImpl): GetCartProductsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindProductDetailsById(getProductDetailsByIdUseCaseImpl: GetProductDetailsByIdUseCaseImpl): GetProductDetailsByIdUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetSaleProducts(getSaleProductsUseCaseImpl: GetSaleProductsUseCaseImpl): GetSaleProductsUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindSearchProduct(searchProductUseCaseImpl: SearchProductUseCaseImpl): SearchProductUseCase

    //Local
    @Binds
    @ViewModelScoped
    abstract fun bindAddToFavourite(addToFavouriteUseCaseImpl: AddToFavouriteUseCaseImpl): AddToFavouriteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteAllFromFavourite(deleteAllFromFavouriteUseCaseImpl: DeleteAllFromFavouriteUseCaseImpl): DeleteAllFromFavouriteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindDeleteFromFavourite(deleteFromFavouriteUseCaseImpl: DeleteFromFavouriteUseCaseImpl): DeleteFromFavouriteUseCase

    @Binds
    @ViewModelScoped
    abstract fun bindGetAllFavouriteProducts(getAllFavouriteProductsUseCaseImpl: GetAllFavouriteProductsUseCaseImpl): GetAllFavouriteProductsUseCase

}