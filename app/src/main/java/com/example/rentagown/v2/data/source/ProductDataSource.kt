package com.example.rentagown.v2.data.source

import com.example.rentagown.v2.data.model.BasePagingResp
import com.example.rentagown.v2.data.model.BaseResp
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ProductReview
import io.reactivex.rxjava3.core.Single

interface ProductDataSource {

    fun getSimilarProducts(categoryName: String): Single<BasePagingResp<Product>>
    fun getProductDetail(productId: String): Single<BaseResp<Product>>
    fun getProductRatings(productId: String): Single<BaseResp<List<ProductReview>>>

}