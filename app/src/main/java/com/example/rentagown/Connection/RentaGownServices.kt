package com.example.rentagown.Connection

import android.graphics.Bitmap
import com.example.rentagown.Body.*
import com.example.rentagown.Response.CheckDate.ResponseCheckDate
import com.example.rentagown.Response.CreateAddress.ResponseAddAddress
import com.example.rentagown.Response.CreateWishlist.ResponseCreateWishlist
import com.example.rentagown.Response.EditProfile.ResponseUploadPict
import com.example.rentagown.Response.FavoriteGown.ResponseFavoriteGown
import com.example.rentagown.Response.GetAddress.ResponseGetAddress
import com.example.rentagown.Response.GetBank.ResponseGetBank
import com.example.rentagown.Response.GetWishlist.ResponseGetWishlist
import com.example.rentagown.Response.Login.ResponseLogin
import com.example.rentagown.Response.MyBooking.ResponseMyBooking
import com.example.rentagown.Response.NewGown.ResponseNewGown
import com.example.rentagown.Response.Notification.ResponseNotifPromo
import com.example.rentagown.Response.Notification.ResponseNotification
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

    //Add Address
    @POST(Constants.ADD_ADDRESS)
    fun addAddress(@HeaderMap map: MutableMap<String, String>?, @Body addressBody: AddAddressBody): Call<ResponseAddAddress>

    //Login
    @POST(Constants.REGISTER)
    fun register(@HeaderMap map: MutableMap<String, String>?, @Body registerBody: RegisterBody): Call<ResponseRegister>

    //Upload Picture
    @Multipart
    @PUT(Constants.UPLOAD_PROFILE_PICT) // end point dari upload
    fun uploadProfilePict(@Part body: MultipartBody.Part) : Call<ResponseUploadPict>

    //Get Address
    @GET(Constants.GET_ADDRESS)
    fun getAddress(@HeaderMap map: MutableMap<String, String>?): Call<ResponseGetAddress>

    //Get Wishlist
    @GET(Constants.GET_WISHLIST)
    fun getWishlist(@HeaderMap map: MutableMap<String, String>?): Call<ResponseGetWishlist>

    //Get Notification
    @GET(Constants.GET_NOTIFICATION)
    fun getNotification(@HeaderMap map: MutableMap<String, String>?): Call<ResponseNotification>

    //Get Notification Promo
    @GET(Constants.GET_NOTIF_PROMO)
    fun getNotifPromo(@HeaderMap map: MutableMap<String, String>?): Call<ResponseNotifPromo>

    //Get Bank
    @GET(Constants.GET_BANK)
    fun getBank(@HeaderMap map: MutableMap<String, String>?): Call<ResponseGetBank>

    //Add Wishlist
    @POST(Constants.ADD_WISHLIST)
    fun addWishlist(@HeaderMap map: MutableMap<String, String>?, @Body wishlistBody: WishlistBody): Call<ResponseCreateWishlist>

    //Get Check Date
    @POST
    fun checkDate(@Url url: String, @HeaderMap map: MutableMap<String, String>?, @Body checkDateBody: CheckDateBody): Call<ResponseCheckDate>
}