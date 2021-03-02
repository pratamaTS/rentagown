package com.example.rentagown.v2.ui.choosebank

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Bank
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.choosebank.item.BankItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.adapters.ModelAdapter
import com.mikepenz.fastadapter.listeners.addClickListener

class ChooseBankActivity : BaseRAGActivity<ChooseBankContract.Presenter>(), ChooseBankContract.View {

    companion object {
        const val REQ_CHOOSE_BANK = 156
        const val RES_BANK_CHOSEN = 157
    }

    override val layoutId = R.layout.activity_choose_bank_v2
    override var contentContainerId: Int = R.id.rv_list_bank

    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: ChooseBankContract.Presenter

    private lateinit var adapter: FastAdapter<BankItem>
    private lateinit var itemAdapter: ModelAdapter<Bank, BankItem>

    private lateinit var rvListBank: RecyclerView

    override fun init() {
        super.init()

        presenter = ChooseBankPresenter(
            RepositoryLocator
                .getInstance(
                    RemoteRepositoryLocator
                        .getInstance(RAGApi.apiService(this))
                )
                .bankRepository
        )
    }

    override fun setupWidgets() {
        super.setupWidgets()

        rvListBank = findViewById(R.id.rv_list_bank)
    }

    override fun setupAdapter() {
        super.setupAdapter()

        itemAdapter = ModelAdapter { m -> BankItem(m, getSelectedBankId()) }
        adapter = FastAdapter.with(itemAdapter)

        rvListBank.layoutManager = LinearLayoutManager(this)
        rvListBank.adapter = adapter

        adapter.addClickListener(resolveView = { null }, resolveViews = { vh: BankItem.ViewHolder -> listOf(vh.container)}) {
            _, _, _, item ->
            presenter.selectBank(item.model)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.loadBanks()
    }

    override fun showBanks(banks: List<Bank>) {
        itemAdapter.set(banks)
    }

    override fun setResultSelectBank(bank: Bank) {
        Intent().apply {
            putExtra("selected_bank", bank)
            setResult(RES_BANK_CHOSEN, this)
            finish()
        }
    }

    private fun getSelectedBankId() = intent.getStringExtra("selected_bank_id")


}