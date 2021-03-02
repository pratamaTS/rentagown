package com.example.rentagown.v2.ui.productdetail.productoverview

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.source.ProductDataSource
import com.example.rentagown.v2.util.Utils
import java.time.LocalDate
import java.util.*

class ProductOverviewPresenter(private val repository: ProductDataSource) : BaseRAGPresenter<ProductOverviewContract.View>(), ProductOverviewContract.Presenter {

    override fun onProductDetailLoaded(productId: String?, product: Product?) {
        product?.apply {
            view?.setProductDataToView(this)
        }
    }

    override fun loadSimilarProducts(categoryName: String) {
        view?.showLoadingContent(true)

        safeCallPaging(repository.getSimilarProducts(categoryName), object : Listener<List<Product>> {
            override fun onSuccess(data: List<Product>?) {
                view?.showSimilarProducts(data ?: listOf())
            }
        })
    }

    override fun onBookingDateSelected(startDateMillis: Long, endDateMillis: Long) {
        val cStart = Calendar.getInstance()
        cStart.timeInMillis = startDateMillis

        val cEnd = Calendar.getInstance()
        cEnd.timeInMillis = endDateMillis

        val dtStart = LocalDate.of(cStart.get(Calendar.YEAR), cStart.get(Calendar.MONTH) + 1, cStart.get(Calendar.DAY_OF_MONTH))
        val dtEnd = LocalDate.of(cEnd.get(Calendar.YEAR), cEnd.get(Calendar.MONTH) + 1, cEnd.get(Calendar.DAY_OF_MONTH))

        view?.setBookingDatesValue(cStart, cEnd)
        view?.showValueOrDefaultBookingDate(Utils.DATE_FORMAT_PRODUCT.format(dtStart), Utils.DATE_FORMAT_PRODUCT.format(dtEnd))
    }

    override fun onBtnStartBookingDateClicked(startDate: Calendar?, endDate: Calendar?) {
        view?.navigateToSelectDates(startDate?.timeInMillis, endDate?.timeInMillis)
    }

    override fun onBtnEndBookingDateClicked(startDate: Calendar?, endDate: Calendar?) {
        view?.navigateToSelectDates(startDate?.timeInMillis, endDate?.timeInMillis)
    }

    override fun onBtnSeeNoAvailableDateClicked(productId: String) {
        view?.navigateToSeeUnAvailableDates(productId)
    }

    override fun onOtherProductClicked(product: Product) {
        view?.navigateToOtherProduct(product)
    }

}