package com.example.rentagown.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.Adapter.ChangeAddressAdapter
import com.example.rentagown.Model.ChangeAddress
import com.example.rentagown.R
import java.util.*
import kotlin.collections.ArrayList

class ChangeAddressActivity : AppCompatActivity(), View.OnClickListener {
    var rvListAddress: RecyclerView? = null
    var back: ImageButton? = null
    var addAddress: ImageButton? = null
    var changeAddressList: ArrayList<ChangeAddress>? = null
    var adapter: ChangeAddressAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_address)

        //INIT VIEW
        rvListAddress = findViewById(R.id.rv_list_address)
        back = findViewById(R.id.im_back)
        addAddress = findViewById(R.id.im_add_address)

        //List Address
        changeAddressList = ArrayList()
        changeAddressList!!.add(
            ChangeAddress(
                "Rumah",
                "John Doe",
                "6287871180436",
                "Perum. Visenda, Jl. K.H. Hasyim, Kec. Kembangan, Kota Jakarta Barat, DKI Jakarta"
            )
        )
        changeAddressList!!.add(
            ChangeAddress(
                "Kantor",
                "John Doe",
                "6287871180436",
                "Gedung Indovision, Jln Martidilaga No. 1, Grogol, Jakarta Barat"
            )
        )
        changeAddressList!!.add(
            ChangeAddress(
                "Rumah",
                "John Doe",
                "6287871180436",
                "Perum. Visenda, Jl. K.H. Hasyim, Kec. Kembangan, Kota Jakarta Barat, DKI Jakarta."
            )
        )

        //Setup Adapter
        adapter = ChangeAddressAdapter(changeAddressList!!, this)
        rvListAddress!!.setLayoutManager(LinearLayoutManager(this))
        rvListAddress!!.setAdapter(adapter)


        //SET LISTENER
        back!!.setOnClickListener(this)
        addAddress!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.im_add_address -> {
                val addAddress = Intent(this@ChangeAddressActivity, AddAddressActivity::class.java)
                startActivity(addAddress)
            }
        }
    }
}