package com.example.rentagown.v2.ui.productdetail.viewphoto

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ProductImage
import com.example.rentagown.v2.data.model.ReqCreateBooking
import java.util.*

class FullScreenContract {

    interface View : BaseView<Presenter> {

        fun showProductImages(images: List<ProductImage>)
        fun getSelectedProductId(): String?
        fun addToWishlist()
        fun getSelectedCategory(): String?
        fun isUserLoggedIn(): Boolean
        fun navigateToLogin()
        fun showBaseContent(popBackStack: Boolean = true)

        fun setResultBookingCreated()

    }

    interface Presenter : BasePresenter {

        fun getProductDetail(productId: String)
        fun onBtnAddToWishlistClicked()
        fun onUserLoggedIn()

    }

}