package com.senemyalin.sencoffee.data.api

import com.senemyalin.sencoffee.data.dto.AddToCartRequest
import com.senemyalin.sencoffee.data.dto.AddToCartResponse
import com.senemyalin.sencoffee.data.dto.ClearCartResponse
import com.senemyalin.sencoffee.data.dto.DeleteFromCartRequest
import com.senemyalin.sencoffee.data.dto.DeleteFromCartResponse
import com.senemyalin.sencoffee.data.dto.GetAllProductsResponse
import com.senemyalin.sencoffee.data.dto.GetCartProductsResponse
import com.senemyalin.sencoffee.data.dto.GetProductDetailsByIdResponse
import com.senemyalin.sencoffee.data.dto.GetSaleProductsResponse
import com.senemyalin.sencoffee.data.dto.SearchProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface CoffeeApi {

    @Headers("store:sencoffee")
    @GET("get_products.php")
    suspend fun getAllProducts(): GetAllProductsResponse

    @Headers("store:sencoffee")
    @GET("get_sale_products.php")
    suspend fun getSaleProducts(): GetSaleProductsResponse

    @Headers("store:sencoffee")
    @GET("get_cart_products.php")
    suspend fun getCartProducts(@Query("userId") userId: String): GetCartProductsResponse

    @Headers("store:sencoffee")
    @GET("search_product.php")
    suspend fun searchProduct(@Query("query") searchKey: String): SearchProductResponse

    @Headers("store:sencoffee")
    @GET("get_product_detail.php")
    suspend fun getProductDetailsById(@Query("id") productId: String): GetProductDetailsByIdResponse

    @Headers("store:sencoffee")
    @POST("add_to_cart.php")
    suspend fun addToCard(@Body() addToCartRequest: AddToCartRequest): AddToCartResponse

    @Headers("store:sencoffee")
    @POST("clear_cart.php")
    suspend fun clearCart(@Body() userId: String): ClearCartResponse

    @Headers("store:sencoffee")
    @POST("delete_from_cart.php")
    suspend fun deleteFromCart(@Body() deleteFromCartRequest: DeleteFromCartRequest): DeleteFromCartResponse

}