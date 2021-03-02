package com.example.rentagown.v2.ui.productdetail

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ReqCreateBooking
import com.example.rentagown.v2.data.source.ProductDataSource
import com.example.rentagown.v2.util.Utils
import java.time.LocalDateTime
import java.time.ZoneId

class ProductDetailPresenter(private val repository: ProductDataSource) : BaseRAGPresenter<ProductDetailContract.View>(), ProductDetailContract.Presenter {

    private var productDetail: Product? = null

    override fun getProductDetail(productId: String) {
        safeCall(repository.getProductDetail(productId), object : Listener<Product> {
            override fun onSuccess(data: Product?) {
                productDetail = data

                data?.let { product ->
                    view?.setProductDataToView(product)
                }

                view?.showProductImages(data?.images ?: listOf())
            }
        }, RequestConfiguration(updateLoadingIndicator = false, updateLoadingContentIndicator = false))
    }

    override fun onBtnWhatsapClicked() {
        val number = "+6281806155676"
        view?.navigateToWhatsapp(number)
    }

    override fun onBtnBookNowClicked() {

        val bookingDates = view?.getBookingDates()

        val startDate = bookingDates?.first
        val endDate = bookingDates?.second

        if (startDate != null && endDate != null) {
            if (view?.isUserLoggedIn() == false) {
                view?.navigateToLogin()
                return
            }

            view?.navigateToCart(ReqCreateBooking(
                    productId = productDetail?.productId,
                    paidPrice = productDetail?.finalPrice,
                    startDate = Utils.formatDateCreateBooking(LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault()).toLocalDate()),
                    endDate = Utils.formatDateCreateBooking(LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault()).toLocalDate()),
                    oneDayService = if (view?.getSpecialTreatmentSelected() == true) 1 else 0
            ), productDetail)
        } else {
            view?.showMsgBookingDateNotValid()
        }
    }

    override fun onUserLoggedIn() {
        view?.showBaseContent()
        onBtnBookNowClicked()
    }

    override fun onBookingCreated() {
        view?.setResultBookingCreated()
        view?.closeView()
    }


}

