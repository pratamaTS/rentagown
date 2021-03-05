package com.example.rentagown.v2.ui.productdetail.productreviews

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.ProductReview
import com.example.rentagown.v2.data.source.ProductDataSource

class ProductReviewsPresenter(private val repository: ProductDataSource) : BaseRAGPresenter<ProductReviewsContract.View>(), ProductReviewsContract.Presenter{


    override fun loadProductReviews(productId: String) {

        view?.showLoadingContent(true)
        safeCallPaging(repository.getProductRatings(productId), object : Listener<List<ProductReview>> {
            override fun onSuccess(data: List<ProductReview>?) {
                view?.showProductReviews(data ?: listOf())
            }
        })

    }


}