package com.example.rentagown.v2.ui.productdetail.viewphoto

import android.util.Log
import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ReqCreateBooking
import com.example.rentagown.v2.data.source.ProductDataSource
import com.example.rentagown.v2.util.Utils
import java.time.LocalDateTime
import java.time.ZoneId

class FullScreenPresenter(private val repository: ProductDataSource) : BaseRAGPresenter<FullScreenContract.View>(), FullScreenContract.Presenter {

    private var productDetail: Product? = null

    override fun getProductDetail(productId: String) {
        safeCall(repository.getProductDetail(productId), object : Listener<Product> {
            override fun onSuccess(data: Product?) {
                productDetail = data

                Log.d("images", data?.images.toString())
                view?.showProductImages(data?.images ?: listOf())
            }
        }, RequestConfiguration(updateLoadingIndicator = false, updateLoadingContentIndicator = false))
    }

    override fun onBtnAddToWishlistClicked() {
        view?.addToWishlist()
    }

    override fun onUserLoggedIn() {
        view?.showBaseContent()
        onBtnAddToWishlistClicked()
    }

}

