package com.example.rentagown.v2.ui.productdetail.productoverview

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Product
import java.util.*

class ProductOverviewContract {

    interface View : BaseView<Presenter> {

        fun showSimilarProducts(products: List<Product>)
        fun navigateToSeeUnAvailableDates(productId: String)
        fun navigateToSelectDates(startDate: Long?, endDate: Long?)
        fun setBookingDatesValue(bookingStartDate: Calendar?, bookingEndDate: Calendar?)
        fun getBookingDatesValue(): Pair<Calendar?, Calendar?>
        fun getSpecialTreatmentSelected(): Boolean
        fun showValueOrDefaultBookingDate(bookingStartDate: String?, bookingEndDate: String?)
        fun setProductDataToView(product: Product)
        fun navigateToOtherProduct(product: Product)

    }

    interface Presenter : BasePresenter {

        fun onProductDetailLoaded(productId: String?, product: Product?)
        fun loadSimilarProducts(categoryName: String)
        fun onBookingDateSelected(startDateMillis: Long, endDateMillis: Long)
        fun onBtnStartBookingDateClicked(startDate: Calendar?, endDate: Calendar?)
        fun onBtnEndBookingDateClicked(startDate: Calendar?, endDate: Calendar?)
        fun onBtnSeeNoAvailableDateClicked(productId: String)
        fun onOtherProductClicked(product: Product)

    }

}