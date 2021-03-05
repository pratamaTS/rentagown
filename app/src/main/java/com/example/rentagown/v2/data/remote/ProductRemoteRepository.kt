package com.example.rentagown.v2.data.remote

import com.example.rentagown.v2.data.model.BasePagingResp
import com.example.rentagown.v2.data.model.BaseResp
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ProductReview
import com.example.rentagown.v2.data.network.RAGApiService
import com.example.rentagown.v2.data.source.ProductDataSource
import io.reactivex.rxjava3.core.Single

class ProductRemoteRepository(private val apiService: RAGApiService) : ProductDataSource {

    override fun getSimilarProducts(categorynamee: String): Single<BasePagingResp<Product>> {
        return apiService.getSimilarProducts(categorynamee)
    }

    override fun getProductDetail(productId: String): Single<BaseResp<Product>> {
        return apiService.getProductDetail(productId)
    }

    override fun getProductRatings(productId: String): Single<BasePagingResp<ProductReview>> {
        return apiService.getProductRatings(productId)
    }

}