package com.example.rentagown.v2.ui.editaddress

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator

class EditAddressActivity : BaseRAGActivity<EditAddressContract.Presenter>(), EditAddressContract.View {

    companion object {

        const val REQ_ADD_EDIT_ADDRESS = 1367
        const val RES_ADDRESS_ADDED = 1368
        const val RES_ADDRESS_EDITED = 1369

    }

    override val layoutId: Int = R.layout.activity_edit_address_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: EditAddressContract.Presenter
    private lateinit var tvTitle: TextView

    private lateinit var etAddressName: EditText
    private lateinit var etAddressDetail: EditText
    private lateinit var etReceiverName: EditText
    private lateinit var etReceiverPhoneNumber: EditText
    private lateinit var btnSaveAddress: Button

    override fun setupWidgets() {
        super.setupWidgets()

        tvTitle = findViewById(R.id.tv_title)
        etAddressName = findViewById(R.id.et_address_name)
        etAddressDetail = findViewById(R.id.et_address_detail)
        etReceiverName = findViewById(R.id.et_receiver_name)
        etReceiverPhoneNumber = findViewById(R.id.et_receiver_phone_number)
        btnSaveAddress = findViewById(R.id.btn_save_address)

        btnSaveAddress.setOnClickListener { presenter.onBtnSaveClicked() }
    }

    override fun init() {
        super.init()

        presenter = EditAddressPresenter(
            RepositoryLocator
                .getInstance(
                    RemoteRepositoryLocator
                        .getInstance(RAGApi.apiService(this))
                )
                .addressRepository
        )

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.onStart(getOldAddress())
    }

    override fun setTitleEditing() {
        tvTitle.text = getString(R.string.title_edit_address)
    }

    override fun setTitleAdding() {
        tvTitle.text = getString(R.string.title_add_address)
    }

    override fun setAddressData(address: Address) {
        etAddressName.setText(address.addressLabel)
        etAddressDetail.setText(address.address)
        etReceiverName.setText(address.receiverName)
        etReceiverPhoneNumber.setText(address.receiverPhoneNumber)
    }

    override fun getCurrentAddressData(): Address {
        val oldAddress = intent.getParcelableExtra<Address>("address_data")
        return Address(
            addressId = oldAddress?.addressId,
            addressLabel = etAddressName.text.toString().trim(),
            address = etAddressDetail.text.toString().trim(),
            receiverName = etReceiverName.text.toString().trim(),
            receiverPhoneNumber = etReceiverPhoneNumber.text.toString().trim()
        )
    }

    override fun successAddAddress(address: Address?) {
        Intent().apply {
            putExtra("address_data", address)
            setResult(RES_ADDRESS_ADDED, this)
            finish()
        }
    }

    override fun successEditAddress(address: Address?) {
        Intent().apply {
            putExtra("address_data", address)
            setResult(RES_ADDRESS_EDITED, this)
            finish()
        }
    }

    private fun getOldAddress() = intent.getParcelableExtra<Address>("address_data")

}