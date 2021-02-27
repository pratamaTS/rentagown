package com.example.rentagown.v2.ui.productdetail.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.ui.productdetail.productoverview.ProductOverviewFragment
import com.example.rentagown.v2.ui.productdetail.productreviews.ProductReviewsFragment

class ProductDetailPagerAdapter(act: FragmentActivity,
                                private val productId: String?,
                                private val category: String?,
                                private val selectedProduct: Product?) : FragmentStateAdapter(act)  {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment =  when (position) {
            0 -> ProductOverviewFragment.newInstance()
            1 -> ProductReviewsFragment.newInstance()
            else -> ProductOverviewFragment.newInstance()
        }

        val bundle = Bundle()
        bundle.putString("product_id", productId)
        bundle.putString("category", category)
        bundle.putParcelable("product", selectedProduct)

        fragment.arguments = bundle

        return fragment
    }

}