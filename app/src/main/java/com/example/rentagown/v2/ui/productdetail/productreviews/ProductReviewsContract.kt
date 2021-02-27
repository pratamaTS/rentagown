package com.example.rentagown.v2.ui.productdetail.productreviews

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.ProductReview

class ProductReviewsContract {

    interface View : BaseView<Presenter> {

        fun showProductReviews(reviews: List<ProductReview>)

    }

    interface Presenter : BasePresenter {

        fun loadProductReviews(productId: String)

    }

}