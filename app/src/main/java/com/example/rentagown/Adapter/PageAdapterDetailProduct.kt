package com.example.rentagown.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.rentagown.Activity.ViewProductActivity
import com.example.rentagown.Fragment.OverviewFragment
import com.example.rentagown.Fragment.ReviewFragment
import com.example.rentagown.Response.Product.DataDetailProduct

class PageAdapterDetailProduct(
    viewProductActivity: ViewProductActivity?,
    fm: FragmentManager?,
    var counttab: Int,
    var detailProduct: DataDetailProduct? = null,
    var startDate: String,
    var endDate: String
) :
    FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        //Declare Var
        var bundle: Bundle = Bundle()
        var overviewFragment: OverviewFragment = OverviewFragment()
        var reviewFragment: ReviewFragment = ReviewFragment()

        bundle.putString("id_product", detailProduct?.idProductDet)
        bundle.putString("name_product_category", detailProduct?.nameProductCategory)
        bundle.putString("product_name", detailProduct?.productName)
        bundle.putInt("product_price", detailProduct?.productPrice!!)
        bundle.putInt("final_price", detailProduct?.finalPrice!!)
        bundle.putInt("promo_amount", detailProduct?.promoAmount!!)
        bundle.putInt("product_quantity", detailProduct?.productQuantity!!)
        bundle.putString("start_date", startDate)
        bundle.putString("end_date", endDate)

        return when (position) {
            0 -> {
                overviewFragment.apply {
                    overviewFragment.arguments = bundle
                }
            }
            1 -> {
                reviewFragment.apply {
                    reviewFragment.arguments = bundle
                }
            }
            else -> OverviewFragment()
        }
    }

    override fun getCount(): Int {
        return counttab
    }
}