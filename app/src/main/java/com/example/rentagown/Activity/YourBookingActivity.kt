package com.example.rentagown.Activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.R
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class YourBookingActivity : AppCompatActivity(), View.OnClickListener {
    var back: ImageButton? = null
    var btnWhatsapp: ImageButton? = null
    var btnDelete: ImageButton? = null
    var btnCheckout: Button? = null
    var imProductCart: RoundedImageView? = null
    var tvProductName: TextView? = null
    var tvProductCategory: TextView? = null
    var tvServices: TextView? = null
    var tvProductPrice: TextView? = null
    var tvTotalPriceCart: TextView? = null
    var tvStartDate: TextView? = null
    var tvEndDate: TextView? = null
    var pathPhoto: String? = null
    var idProduct: String? = null
    var productName: String? = null
    var productPrice: Int? = null
    var phone: String? = null
    var services: Int? = null
    var startDate: String? = null
    var endDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_your_booking)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        btnWhatsapp = findViewById(R.id.btn_whatsapp)
        btnCheckout = findViewById(R.id.btn_checkout)
        btnDelete = findViewById(R.id.btn_delete_your_booking)
        imProductCart = findViewById(R.id.im_product_cart)
        tvProductName = findViewById(R.id.tv_name_dress_your_booking)
        tvServices = findViewById(R.id.tv_special_treatment_your_booking)
        tvProductPrice = findViewById(R.id.tv_price_dress_your_booking)
        tvTotalPriceCart = findViewById(R.id.tv_total_price_cart)
        tvStartDate = findViewById(R.id.tv_start_date_your_booking)
        tvEndDate = findViewById(R.id.tv_end_date_your_booking)

        pathPhoto = intent.getStringExtra("path_photo")
        idProduct = intent.getStringExtra("id_product")
        productName = intent.getStringExtra("product_name")
        phone = intent.getStringExtra("phone")
        productPrice = intent.getIntExtra("paid_price", 0)
        services = intent.getIntExtra("services", 0)
        startDate = intent.getStringExtra("start_date")
        endDate = intent.getStringExtra("end_date")

        // Format Date
        val inputFormat = SimpleDateFormat("dd/MM/yyyy")
        val outputFormat = SimpleDateFormat("dd MMMM yyyy")
        val startDateNew: Date = inputFormat.parse(startDate)
        val endDateNew: Date = inputFormat.parse(endDate)
        val startDateConvert: String = outputFormat.format(startDateNew)
        val endDateConvert: String = outputFormat.format(endDateNew)

        // Format Currency
        val localeID =  Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)

        val imgURL: String = "http://absdigital.id:5000" + pathPhoto
        Picasso.get().load(imgURL).into(imProductCart)
        tvProductName!!.text = productName?.capitalize()?.trim()
        tvProductPrice!!.text = numberFormat.format(productPrice)
        tvTotalPriceCart!!.text = numberFormat.format(productPrice)
        when(services){
            0 -> tvServices!!.text = "Regular Service"
            1 -> tvServices!!.text = "One Day Service"
        }
        tvStartDate!!.text = startDateConvert
        tvEndDate!!.text = endDateConvert

        //SET LISTENER
        back!!.setOnClickListener(this)
        btnWhatsapp!!.setOnClickListener(this)
        btnDelete!!.setOnClickListener(this)
        btnCheckout!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_delete_your_booking -> finish()
            R.id.btn_checkout -> {
                val checkout = Intent(this@YourBookingActivity, PaymentActivity::class.java)
                startActivity(checkout)
            }
            R.id.btn_whatsapp -> {
                val number = "+6281806155676"
                val url = "https://api.whatsapp.com/send/?phone=$number"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }
    }
}