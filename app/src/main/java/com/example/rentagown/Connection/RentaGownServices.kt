package com.example.rentagown.Connection

import android.graphics.Bitmap
import com.example.rentagown.Body.LoginBody
import com.example.rentagown.Body.RegisterBody
import com.example.rentagown.Response.EditProfile.ResponseUploadPict
import com.example.rentagown.Response.FavoriteGown.ResponseFavoriteGown
import com.example.rentagown.Response.Login.ResponseLogin
import com.example.rentagown.Response.MyBooking.ResponseMyBooking
import com.example.rentagown.Response.NewGown.ResponseNewGown
import com.example.rentagown.Response.Product.ResponseDetailProduct
import com.example.rentagown.Response.Product.ResponseProduct
import com.example.rentagown.Response.ProductCategory.ResponseProductCategory
import com.example.rentagown.Response.Profile.ResponseProfile
import com.example.rentagown.Response.Promo.PromoDetail.ResponsePromoDetail
import com.example.rentagown.Response.Promo.ResponsePromo
import com.example.rentagown.Response.Register.ResponseRegister
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface RentaGownServices {
    //Get All Product
    @GET(Constants.ALL_PRODUCT)
    fun getAllProduct(@HeaderMap map: MutableMap<String, String>?): Call<ResponseProduct>

    //Get All Product by Category
    @GET
    fun getAllProductByCategory(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponseProduct>

    //Get All Product by Category
    @GET
    fun getAllProductByPromo(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponseProduct>

    //Get All Promo
    @GET(Constants.ALL_PROMO)
    fun getAllPromo(@HeaderMap map: MutableMap<String, String>?): Call<ResponsePromo>

    //Get All Product Category
    @GET(Constants.PRODUCT_CATEGORY)
    fun getAllProductCategory(@HeaderMap map: MutableMap<String, String>?): Call<ResponseProductCategory>

    //Get All New Gown
    @GET(Constants.NEW_GOWN)
    fun getAllNewGown(@HeaderMap map: MutableMap<String, String>?): Call<ResponseNewGown>

    //Get All Favorite Gown
    @GET(Constants.FAV_GOWN)
    fun getAllFavoriteGown(@HeaderMap map: MutableMap<String, String>?): Call<ResponseFavoriteGown>

    //Get Profile
    @GET(Constants.PROFILE)
    fun getProfile(@HeaderMap map: MutableMap<String, String>?): Call<ResponseProfile>

    //Get My Booking
    @GET(Constants.MY_BOOKING)
    fun getMyBooking(@HeaderMap map: MutableMap<String, String>?): Call<ResponseMyBooking>

    //Get Detail Product By ID
    @GET
    fun getDetailProduct(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponseDetailProduct>

    //Get Detail Promo By ID
    @GET
    fun getPromoById(@Url url: String, @HeaderMap map: MutableMap<String, String>?): Call<ResponsePromoDetail>

    //Login
    @POST(Constants.LOGIN)
    fun login(@HeaderMap map: MutableMap<String, String>?, @Body loginBody: LoginBody): Call<ResponseLogin>

    //Login
    @POST(Constants.REGISTER)
    fun register(@HeaderMap map: MutableMap<String, String>?, @Body registerBody: RegisterBody): Call<ResponseRegister>


    @Multipart
    @PUT(Constants.UPLOAD_PROFILE_PICT) // end point dari upload
    fun uploadProfilePict(@Part body: MultipartBody.Part) : Call<ResponseUploadPict>
}