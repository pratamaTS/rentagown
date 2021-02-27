package com.example.rentagown.v2.data.remote

import com.example.rentagown.v2.data.model.BasePagingResp
import com.example.rentagown.v2.data.model.BaseResp
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.model.ProductReview
import com.example.rentagown.v2.data.network.RAGApiService
import com.example.rentagown.v2.data.source.ProductDataSource
import io.reactivex.rxjava3.core.Single

class ProductRemoteRepository(private val apiService: RAGApiService) : ProductDataSource {

    override fun getSimilarProducts(categoryName: String): Single<BasePagingResp<Product>> {
        return apiService.getSimilarProducts(categoryName)
    }

    override fun getProductDetail(productId: String): Single<BaseResp<Product>> {
        return apiService.getProductDetail(productId)
    }

    override fun getProductRatings(productId: String): Single<BaseResp<List<ProductReview>>> {
        // dummy response for now

        val r1 = ProductReview(
            ratingId = "1",
            userName = "Ridwan Surya Putra",
            score = 1.0f,
            reviewDateTime = "1 day ago",
            message = "Barangnya bagus dan sesuai dengan ukuran saya"
        )

        val r2 = ProductReview(
            ratingId = "2",
            userName = "Ridwan Surya Putra",
            score = 2.0f,
            reviewDateTime = "1 day ago",
            message = "Barangnya bagus dan sesuai dengan ukuran saya"
        )

        val r3 = ProductReview(
            ratingId = "3",
            userName = "Ridwan Surya Putra",
            score = 3.0f,
            reviewDateTime = "1 day ago",
            message = "Barangnya bagus dan sesuai dengan ukuran saya"
        )

        val r4 = ProductReview(
            ratingId = "4",
            userName = "Ridwan Surya Putra",
            score = 4.0f,
            reviewDateTime = "1 day ago",
            message = "Barangnya bagus dan sesuai dengan ukuran saya"
        )

        val r5 = ProductReview(
            ratingId = "5",
            userName = "Ridwan Surya Putra",
            score = 5.0f,
            reviewDateTime = "1 day ago",
            message = "Barangnya bagus dan sesuai dengan ukuran saya"
        )

        val r6 = ProductReview(
            ratingId = "6",
            userName = "Ridwan Surya Putra",
            score = 5.0f,
            reviewDateTime = "1 day ago",
            message = "Barangnya bagus dan sesuai dengan ukuran saya"
        )

        val r7 = ProductReview(
            ratingId = "7",
            userName = "Ridwan Surya Putra",
            score = 4.0f,
            reviewDateTime = "1 day ago",
            message = "Barangnya bagus dan sesuai dengan ukuran saya"
        )

        val r8 = ProductReview(
            ratingId = "8",
            userName = "Ridwan Surya Putra",
            score = 4.5f,
            reviewDateTime = "1 day ago",
            message = "Barangnya bagus dan sesuai dengan ukuran saya"
        )

        val r9 = ProductReview(
            ratingId = "9",
            userName = "Ridwan Surya Putra",
            score = 5.0f,
            reviewDateTime = "1 day ago",
            message = "Barangnya bagus dan sesuai dengan ukuran saya"
        )

        val r10 = ProductReview(
            ratingId = "10",
            userName = "Ridwan Surya Putra",
            score = 5.0f,
            reviewDateTime = "1 day ago",
            message = "Barangnya bagus dan sesuai dengan ukuran saya"
        )
//
//        return Single.just(BaseResp(code = 200, data = listOf(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10)))
        return Single.just(BaseResp(code = 200, data = listOf(r1, r2)))
    }

}