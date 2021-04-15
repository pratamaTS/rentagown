package com.example.rentagown.v2.ui.chooseaddress

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Address
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.chooseaddress.item.AddressItem
import com.example.rentagown.v2.ui.editaddress.EditAddressActivity
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.fastadapter.listeners.addClickListener

class ChooseAddressActivity : BaseRAGActivity<ChooseAddressContract.Presenter>(), ChooseAddressContract.View,
        View.OnClickListener {

    companion object {
        const val REQ_CHOOSE_ADDRESS = 412
    }

    override val layoutId = R.layout.activity_choose_address_v2
    override var contentContainerId: Int = R.id.rv_list_address
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: ChooseAddressContract.Presenter

    private lateinit var adapter: FastAdapter<AddressItem>
    private lateinit var itemAdapter: ModelAdapter<Address, AddressItem>

    // widgets
    private lateinit var rvListAddress: RecyclerView
    private lateinit var btnAddAddress: ImageButton

    private lateinit var resultIntent: Intent

    override fun init() {
        super.init()

        presenter = ChooseAddressPresenter(RepositoryLocator
            .getInstance(RemoteRepositoryLocator
                .getInstance(RAGApi.apiService(this)))
                    .addressRepository)

        resultIntent = Intent()
    }

    override fun setupWidgets() {
        super.setupWidgets()

        rvListAddress = findViewById(R.id.rv_list_address)
        btnAddAddress = findViewById(R.id.btn_add_address)

        btnAddAddress.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.loadAddressList()
    }

    override fun setupAdapter() {
        super.setupAdapter()

        itemAdapter = ModelAdapter { m -> AddressItem(m, getSelectedAddressId()) }
        adapter = FastAdapter.with(itemAdapter)

        val lm = LinearLayoutManager(this)
        rvListAddress.layoutManager = lm
        rvListAddress.adapter = adapter

        adapter.addClickListener(resolveView = { null }, resolveViews = { vh: AddressItem.ViewHolder -> listOf(vh.btnChooseAddress, vh.btnEditAddress, vh.btnDeleteAddress)}) {
            v, _, _, item ->
            when (v.id) {
                R.id.btn_choose_address -> presenter.onBtnChooseAddressClicked(item.model)
                R.id.btn_edit_address -> presenter.onBtnEditAddressClicked(item.model)
                R.id.btn_delete_address -> presenter.onBtnDeleteAddressClicked(item.model)
            }
        }
    }

    override fun showMessageSuccessChangeAddress() { }

    override fun showAddressList(addressList: List<Address>) {
        itemAdapter.set(addressList)
    }

    override fun chooseAddress(address: Address) {
        when(address.isDefault){
            0 -> {
                val msg1 = "Do you want to set this address to default?"
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Confirmation")
                    .setMessage(msg1)
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        resultIntent.putExtra("selected_address", address)
                        resultIntent.putExtra("default", true)
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        resultIntent.putExtra("selected_address", address)
                        resultIntent.putExtra("default", false)
                        setResult(RESULT_OK, resultIntent)
                        finish()
                    }
                val alert = builder.create()
                alert.show()
            }
            1 -> {
                resultIntent.putExtra("selected_address", address)
                resultIntent.putExtra("default", false)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }

    override fun navigateToEditAddress(address: Address) {
        Intent(this@ChooseAddressActivity, EditAddressActivity::class.java).apply {
            putExtra("address_data", address)
            startActivityForResult(this, EditAddressActivity.REQ_ADD_EDIT_ADDRESS)
        }
    }

    override fun navigateToAddAddress() {
        Intent(this@ChooseAddressActivity, EditAddressActivity::class.java).apply {
            startActivityForResult(this, EditAddressActivity.REQ_ADD_EDIT_ADDRESS)
        }
    }

    override fun removeAddress(addressId: String) {
        itemAdapter.adapterItems.indexOfFirst { addressItem -> !addressItem.model.addressId.isNullOrBlank() && addressItem.model.addressId == addressId }
            .takeIf { pos -> pos > -1 }
            ?.let { pos ->
                itemAdapter.remove(pos)
            }
    }

    override fun editAddress(address: Address) {
        itemAdapter.adapterItems.indexOfFirst { addressItem -> !addressItem.model.addressId.isNullOrBlank() && addressItem.model.addressId == address.addressId }
            .takeIf { pos -> pos > -1 }
            ?.let { pos ->
                itemAdapter.getAdapterItem(pos).model = address
                adapter.notifyAdapterItemChanged(itemAdapter.getGlobalPosition(pos))
            }
    }

    override fun addAddress(address: Address) {
        itemAdapter.add(address)
        rvListAddress.scrollToPosition(adapter.itemCount - 1)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_add_address -> presenter.onBtnAddAddressClicked()
        }
    }

    override fun getSelectedAddressId() = intent.getStringExtra("selected_address_id")

    override fun setResultAddressEdited(address: Address) {
        resultIntent.putExtra("edited_address", address)
        setResult(RESULT_OK, resultIntent)
    }

    override fun setResultAddressDeleted(address: Address) {
        resultIntent.putExtra("deleted_address", address)
        setResult(RESULT_OK, resultIntent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == EditAddressActivity.REQ_ADD_EDIT_ADDRESS) {
            if(resultCode == EditAddressActivity.RES_ADDRESS_ADDED) {
                presenter.onAddressAdded(data?.getParcelableExtra("address_data"))
            } else if(resultCode == EditAddressActivity.RES_ADDRESS_EDITED) {
                presenter.onAddressEdited(data?.getParcelableExtra("address_data"))
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

}