package com.example.rentagown.v2.ui.productdetail.productreviews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGFragment
import com.example.rentagown.v2.data.model.ProductReview
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.productdetail.productreviews.item.ProductReviewItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter

class ProductReviewsFragment : BaseRAGFragment<ProductReviewsContract.Presenter>(), ProductReviewsContract.View {

    companion object {
        fun newInstance() : Fragment {
            return ProductReviewsFragment()
        }
    }

    override val layoutId: Int = R.layout.fragment_product_reviews_v2
    override val TAG: String = "ProductReviewsFragment"

    override lateinit var presenter: ProductReviewsContract.Presenter

    override var contentContainerId: Int = R.id.rv_product_review_list

    private lateinit var adapter: FastAdapter<ProductReviewItem>
    private lateinit var itemAdapter: ModelAdapter<ProductReview, ProductReviewItem>

    private lateinit var rvProductReviewList: RecyclerView

    override fun init() {
        super.init()

        presenter = ProductReviewsPresenter(
            RepositoryLocator
            .getInstance(
                RemoteRepositoryLocator
                .getInstance(RAGApi.apiService(requireActivity())))
            .productRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString("product_id", "")?.let { productId ->
            presenter.loadProductReviews(productId)
        }

    }

    override fun setupWidgets(v: View) {
        super.setupWidgets(v)

        rvProductReviewList = v.findViewById(R.id.rv_product_review_list)
    }

    override fun setupAdapter() {
        super.setupAdapter()

        itemAdapter = ModelAdapter { m -> ProductReviewItem(m) }
        adapter = FastAdapter.with(itemAdapter)

        rvProductReviewList.layoutManager = LinearLayoutManager(context)
        rvProductReviewList.adapter = adapter

    }

    override fun showProductReviews(reviews: List<ProductReview>) {
        itemAdapter.set(reviews)
    }

}