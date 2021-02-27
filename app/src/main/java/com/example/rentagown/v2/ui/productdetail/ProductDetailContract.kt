package com.example.rentagown.v2.ui.productdetail

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ProductImage
import com.example.rentagown.v2.data.model.ReqCreateBooking
import java.util.*

class ProductDetailContract {

    interface View : BaseView<Presenter> {

        fun showProductImages(images: List<ProductImage>)
        fun getSelectedProductId(): String?
        fun getUserPhoneNumber(): String?
        fun navigateToWhatsapp(phoneNumber: String)
        fun getBookingDates(): Pair<Calendar?, Calendar?>
        fun getSpecialTreatmentSelected(): Boolean
        fun navigateToBooking(createBooking: ReqCreateBooking, product: Product?)
        fun setProductDataToView(product: Product)
        fun getSelectedCategory(): String?
        fun isUserLoggedIn(): Boolean
        fun navigateToLogin()
        fun showBaseContent(popBackStack: Boolean = true)

        fun showMsgBookingDateNotValid()

        fun setResultBookingCreated()

    }

    interface Presenter : BasePresenter {

        fun getProductDetail(productId: String)
        fun onBtnWhatsapClicked()
        fun onBtnBookNowClicked()
        fun onUserLoggedIn()

        fun onBookingCreated()

    }

}