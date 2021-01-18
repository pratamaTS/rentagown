package com.example.rentagown.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.CartAdapter
import com.example.rentagown.Model.Cart
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class CartFragment : Fragment(), View.OnClickListener {
    var rvCart: RecyclerView? = null
    var cartList: ArrayList<Cart>? = null
    var cartAdapter: CartAdapter? = null
    var btnCheckout: Button? = null
    var btnWhatsapp: ImageButton? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_cart, container, false)

        //INIT VIEW
        rvCart = view.findViewById(R.id.rv_cart_booking)
        btnCheckout = view.findViewById(R.id.btn_checkout)
        btnWhatsapp = view.findViewById(R.id.btn_whatsapp)

        //List Cart
        cartList = ArrayList()
        cartList!!.add(
            Cart(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Prewedding Gown",
                "One Day Service",
                "Rp. 5.000.000",
                "9 September 2020 - 12 September 2020"
            )
        )
        cartList!!.add(
            Cart(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Wedding Gown",
                "One Day Service",
                "Rp. 10.000.000",
                "9 September 2020 - 12 September 2020"
            )
        )
        cartList!!.add(
            Cart(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Family Gown",
                "One Day Service",
                "Rp. 20.000.000",
                "9 September 2020 - 12 September 2020"
            )
        )
        cartList!!.add(
            Cart(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Prewedding Gown",
                "One Day Service",
                "Rp. 16.000.000",
                "9 September 2020 - 12 September 2020"
            )
        )
        cartList!!.add(
            Cart(
                R.drawable.prewedding_1,
                "Selina Colourblock Camisole Dress",
                "Prewedding Gown",
                "One Day Service",
                "Rp. 2.500.000",
                "9 September 2020 - 12 September 2020"
            )
        )

        //Setup Recycler View
        cartAdapter = CartAdapter(context!!, cartList!!)
        rvCart!!.setLayoutManager(LinearLayoutManager(activity))
        rvCart!!.setAdapter(cartAdapter)

        //SET LISTENER
        btnCheckout!!.setOnClickListener(this@CartFragment)
        btnWhatsapp!!.setOnClickListener(this@CartFragment)
        return view
    }

    override fun onClick(v: View) {}
}