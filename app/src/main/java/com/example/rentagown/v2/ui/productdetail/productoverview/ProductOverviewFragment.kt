package com.example.rentagown.v2.ui.productdetail.productoverview

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Activity.MainActivity
import com.example.rentagown.Activity.MainAfterActivity
import com.example.rentagown.Body.WishlistBody
import com.example.rentagown.Connection.Interface.AddWishlistInterface
import com.example.rentagown.Connection.Presenter.AddWishlistPresenter
import com.example.rentagown.Connection.SessionManager
import com.example.rentagown.Fragment.LoginFragment
import com.example.rentagown.R
import com.example.rentagown.Response.CreateWishlist.DataAddWishlist
import com.example.rentagown.v2.base.BaseRAGFragment
import com.example.rentagown.v2.data.model.Product
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.choosedate.ChooseDateActivity
import com.example.rentagown.v2.ui.productdetail.ProductDetailActivity
import com.example.rentagown.v2.ui.productdetail.productoverview.item.ProductItem
import com.example.rentagown.v2.ui.unvailabledates.UnavailableDatesActivity
import com.example.rentagown.v2.util.Utils
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.fastadapter.listeners.addClickListener
import java.util.*

class ProductOverviewFragment : BaseRAGFragment<ProductOverviewContract.Presenter>(), ProductOverviewContract.View, AddWishlistInterface,
        View.OnClickListener {

    companion object {

        fun newInstance(): Fragment {
            return ProductOverviewFragment()
        }

    }

    override val layoutId: Int = R.layout.fragment_product_overview_v2
    override val TAG: String = "ProductOverviewFragment"

    override lateinit var presenter: ProductOverviewContract.Presenter

    private lateinit var adapter: FastAdapter<ProductItem>
    private lateinit var itemAdapter: ModelAdapter<Product, ProductItem>

    private lateinit var tvFinalPrice: TextView
    private lateinit var tvDiscountValue: TextView
    private lateinit var tvProductName: TextView
    private lateinit var tvPrice: TextView

    private lateinit var rvSimilarProducts: RecyclerView

    private lateinit var containerBookingStartDate: View
    private lateinit var containerBookingEndDate: View
    private lateinit var btnSeeNoAvailableDates: Button

    private lateinit var tvBookingStartDate: TextView
    private lateinit var tvBookingEndDate: TextView

    private lateinit var swSpecialTreatment: SwitchCompat

    private lateinit var btnLike: ImageButton

    private var selectedStartDate: Calendar? = null
    private var selectedEndDate: Calendar? = null
    private var token: String? = null


    override fun init() {
        super.init()

        presenter = ProductOverviewPresenter(
            RepositoryLocator
                .getInstance(
                    RemoteRepositoryLocator
                        .getInstance(RAGApi.apiService(requireActivity())))
                .productRepository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getSelectedCategory()?.let { category ->
            presenter.loadSimilarProducts(category)
        }

    }

    override fun setupWidgets(v: View) {
        super.setupWidgets(v)

        val sessionManager = SessionManager(context!!)

        tvFinalPrice = v.findViewById(R.id.tv_final_price)
        tvDiscountValue = v.findViewById(R.id.tv_discount_value)
        tvProductName = v.findViewById(R.id.tv_product_name)
        tvPrice = v.findViewById(R.id.tv_price)

        rvSimilarProducts = v.findViewById(R.id.rv_similar_products)

        containerBookingStartDate = v.findViewById(R.id.container_booking_start_date)
        containerBookingEndDate = v.findViewById(R.id.container_booking_end_date)
        btnSeeNoAvailableDates = v.findViewById(R.id.btn_see_no_available_date)
        tvBookingStartDate = v.findViewById(R.id.tv_booking_start_date)
        tvBookingEndDate = v.findViewById(R.id.tv_booking_end_date)

        swSpecialTreatment = v.findViewById(R.id.sw_special_treatment)

        btnLike = v.findViewById(R.id.btn_like)

        sessionManager.fetchAuthToken()?.let {
            token = it
        }

        containerBookingStartDate.setOnClickListener(this)
        containerBookingEndDate.setOnClickListener(this)
        btnSeeNoAvailableDates.setOnClickListener(this)
        btnLike.setOnClickListener(this)

    }

    override fun setupAdapter() {
        super.setupAdapter()

        itemAdapter = ModelAdapter { m -> ProductItem(m) }
        adapter = FastAdapter.with(itemAdapter)

        rvSimilarProducts.layoutManager = GridLayoutManager(context, 2)
        rvSimilarProducts.adapter = adapter

        adapter.addClickListener(resolveView = { vh: ProductItem.ViewHolder -> vh.btnBookingNow },
                resolveViews = { listOf() }) { _, _, _, item ->
            presenter.onOtherProductClicked(item.model)
        }
    }

    override fun showSimilarProducts(products: List<Product>) {
        itemAdapter.set(products)
    }

    override fun navigateToSeeUnAvailableDates(productId: String) {
        activity?.let { act ->
            Intent(act, UnavailableDatesActivity::class.java).apply {
                putExtra("product_id", getSelectedProductId())
                startActivity(this)
            }
        }
    }

    override fun navigateToSelectDates(startDate: Long?, endDate: Long?) {

        Intent(activity, ChooseDateActivity::class.java).apply {
            putExtra("selected_start_date", startDate)
            putExtra("selected_end_date", endDate)
            startActivityForResult(this, ChooseDateActivity.REQ_PICK_DATE_RANGE)
        }

    }

    override fun setBookingDatesValue(bookingStartDate: Calendar?, bookingEndDate: Calendar?) {
        this.selectedStartDate = bookingStartDate
        this.selectedEndDate = bookingEndDate
    }

    override fun getBookingDatesValue(): Pair<Calendar?, Calendar?> {
        return Pair(selectedStartDate, selectedEndDate)
    }

    override fun getSpecialTreatmentSelected() = swSpecialTreatment.isChecked

    override fun showValueOrDefaultBookingDate(bookingStartDate: String?, bookingEndDate: String?) {
        tvBookingStartDate.text = if(bookingStartDate.isNullOrBlank()) getString(R.string.dd_mm_yyyy) else bookingStartDate
        tvBookingEndDate.text = if(bookingEndDate.isNullOrBlank()) getString(R.string.dd_mm_yyyy) else bookingEndDate
    }

    @SuppressLint("SetTextI18n")
    override fun setProductDataToView(product: Product) {
        tvDiscountValue.visibility = if(product.promoAmount != null && product.promoAmount > 0) View.VISIBLE else View.GONE
        tvPrice.visibility = if(product.promoAmount != null && product.promoAmount > 0) View.VISIBLE else View.GONE

        tvFinalPrice.text = Utils.formatMoney(product.finalPrice)
        tvDiscountValue.text = product.promoAmount.toString() + getString(R.string.sym_discount_amount)
        tvPrice.text = Utils.formatMoney(product.productPrice)
        tvProductName.text = product.productName

        when(product.isWishList){
            1 -> btnLike.setBackgroundResource(R.drawable.ic_like_gold_filled)
        }
    }

    override fun navigateToOtherProduct(product: Product) {
        Intent(activity, ProductDetailActivity::class.java).apply {
            putExtra("product_id", product.productId)
            putExtra("category", product.productCategoryName)
            activity?.startActivityForResult(this, ProductDetailActivity.REQ_VIEW_PRODUCT_DETAIL)

            activity?.recreate()
        }
    }

    private fun getSelectedProductId() = arguments?.getString("product_id", null)

    private fun getSelectedCategory() = arguments?.getString("category", null)

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.container_booking_start_date -> presenter.onBtnStartBookingDateClicked(selectedStartDate, selectedEndDate)
            R.id.container_booking_end_date -> presenter.onBtnStartBookingDateClicked(selectedStartDate, selectedEndDate)
            R.id.btn_see_no_available_date -> {
                getSelectedProductId()?.apply { presenter.onBtnSeeNoAvailableDateClicked(this) }
            }
            R.id.btn_like -> {
                if(token != null) {
                    var productID = ""
                    getSelectedProductId()?.apply { productID = this }
                    AddWishlistPresenter(this).addWishlist(context!!, WishlistBody(productID))
                }else{
                    activity?.let { act ->
                        Intent(act, MainActivity::class.java).apply {
                            putExtra("login_check", true)
                            startActivity(this)
                        }
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == ChooseDateActivity.REQ_PICK_DATE_RANGE) {
            if(resultCode == ChooseDateActivity.RES_DATE_PICKED) {
                presenter.onBookingDateSelected(data?.getLongExtra("selected_start_date", -1) ?: -1,
                    data?.getLongExtra("selected_end_date", -1) ?: -1)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSuccessAddWishlist(dataAddWishlist: DataAddWishlist) {
        btnLike.setBackgroundResource(R.drawable.ic_like_gold_filled)
    }

    override fun onErrorAddWishlist(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }


}