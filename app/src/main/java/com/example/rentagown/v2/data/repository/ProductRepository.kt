package com.example.rentagown.v2.data.repository

import com.example.rentagown.v2.data.model.BasePagingResp
import com.example.rentagown.v2.data.model.BaseResp
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ProductReview
import com.example.rentagown.v2.data.source.ProductDataSource
import io.reactivex.rxjava3.core.Single

class ProductRepository(private val remoteRepository: ProductDataSource) : ProductDataSource {

    override fun getSimilarProducts(categoryName: String): Single<BasePagingResp<Product>> {
        return remoteRepository.getSimilarProducts(categoryName)
    }

    override fun getProductDetail(productId: String): Single<BaseResp<Product>> {
        return remoteRepository.getProductDetail(productId)
    }

    override fun getProductRatings(productId: String): Single<BaseResp<List<ProductReview>>> {
        return remoteRepository.getProductRatings(productId)
    }

}