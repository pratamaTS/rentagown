package com.example.rentagown.Connection.Interface

import com.example.rentagown.Body.LoginBody
import com.example.rentagown.Response.FavoriteGown.ResponseFavoriteGown
import com.example.rentagown.Response.Login.ResponseLogin
import com.example.rentagown.Response.NewGown.ResponseNewGown
import com.example.rentagown.Response.Product.ResponseDetailProduct
import com.example.rentagown.Response.Product.ResponseProduct
import com.example.rentagown.Response.ProductCategory.ResponseProductCategory
import com.example.rentagown.Response.Profile.ResponseProfile
import com.example.rentagown.Response.Promo.PromoDetail.ResponsePromoDetail
import com.example.rentagown.Response.Promo.ResponsePromo
import retrofit2.Call
import retrofit2.http.*

interface RentaGownServices {
    //Get All Product
    @GET("api/v/1/product/findall")
    fun getAllProduct(@HeaderMap map: MutableMap<String, String>?): Call<ResponseProduct>

    //Get All Product by Category
    @GET
    fun getAllProductByCategory(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponseProduct>

    //Get All Promo
    @GET("api/v/1/promo/findall")
    fun getAllPromo(@HeaderMap map: MutableMap<String, String>?): Call<ResponsePromo>

    //Get All Product Category
    @GET("api/v/1/prodcat/findall")
    fun getAllProductCategory(@HeaderMap map: MutableMap<String, String>?): Call<ResponseProductCategory>

    //Get All New Gown
    @GET("api/v/1/product/findsort/desc")
    fun getAllNewGown(@HeaderMap map: MutableMap<String, String>?): Call<ResponseNewGown>

    //Get All Favorite Gown
    @GET("api/v/1/user/favorite")
    fun getAllFavoriteGown(@HeaderMap map: MutableMap<String, String>?): Call<ResponseFavoriteGown>

    //Get Profile
    @GET("api/v/1/user/profile")
    fun getProfile(@HeaderMap map: MutableMap<String, String>?): Call<ResponseProfile>

    //Get Detail Product By ID
    @GET
    fun getDetailProduct(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponseDetailProduct>

    //Get Detail Promo By ID
    @GET
    fun getPromoById(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponsePromoDetail>

    //Login
    @POST("api/v/1/user/login")
    fun login(@HeaderMap map: MutableMap<String, String>?, @Body loginBody: LoginBody): Call<ResponseLogin>
}