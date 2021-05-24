package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rentagown.BuildConfig
import com.example.rentagown.Connection.Interface.PromoByIdInterface
import com.example.rentagown.Connection.Presenter.PromoByIdPresenter
import com.example.rentagown.R
import com.example.rentagown.Response.Promo.PromoDetail.DataPromoDetail
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso

class DetailPromoActivity : AppCompatActivity(), View.OnClickListener, PromoByIdInterface {
    var back: ImageButton? = null
    var btnUsePromo: Button? = null
    var promoDetail: DataPromoDetail? = null
    var idPromo: String? = null
    var imPromo: RoundedImageView? = null
    var tvTitleDetailProno: TextView? = null
    var tvDescDetailPromo: TextView? = null
    var tvTermCondition: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_promo)

        //INIT VIEW
        imPromo = findViewById(R.id.im_item_promo)
        tvTitleDetailProno = findViewById(R.id.tv_title_item_detail_promo)
        tvDescDetailPromo = findViewById(R.id.tv_desc_item_list_promo)
        tvTermCondition = findViewById(R.id.field_tems_and_conditions_promo)
        back = findViewById(R.id.im_back)
        btnUsePromo = findViewById(R.id.btn_use_promo)

        idPromo = intent.getStringExtra("id_promo")

        getDetailPromo()

        //SET LISTENER
        back?.setOnClickListener(this)
        btnUsePromo?.setOnClickListener(this)
    }

    private fun getDetailPromo() {
        PromoByIdPresenter(this).getPromoById(idPromo.toString())
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_use_promo -> {
                val categoryProduct = Intent(this, ProductGownActivity::class.java)
                categoryProduct.putExtra("check_promo", true)
                categoryProduct.putExtra("id_promo", idPromo)
                startActivity(categoryProduct)
            }
        }
    }

    override fun onSuccessGetPromoById(dataPromoById: DataPromoDetail?) {
        promoDetail = dataPromoById

        tvTitleDetailProno!!.text = promoDetail?.promoName?.capitalize()?.trim()

        if(promoDetail!!.promoDesc?.isNotEmpty() == true){
            tvDescDetailPromo!!.text = promoDetail!!.promoDesc
        }

        if(promoDetail!!.termsConditions?.isNotEmpty() == true){
            tvTermCondition!!.text = promoDetail!!.termsConditions
        }

        if(promoDetail!!.pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = BuildConfig.BASE_PHOTO_URL + promoDetail!!.pathPhoto
            Picasso.get().load(imgURL).resize(200, 200).into(imPromo)
        }
    }

    override fun onErrorGetPromoById(msg: String) {
        Toast.makeText(this, "Failed to get data detail promo", Toast.LENGTH_SHORT)
    }
}