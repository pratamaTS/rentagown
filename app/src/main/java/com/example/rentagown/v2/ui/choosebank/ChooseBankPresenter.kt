package com.example.rentagown.v2.ui.choosebank

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Bank
import com.example.rentagown.v2.data.source.BankDataSource

class ChooseBankPresenter(private val repository: BankDataSource) : BaseRAGPresenter<ChooseBankContract.View>(), ChooseBankContract.Presenter {

    override fun loadBanks() {
        view?.showLoadingContent(true)
        safeCallPaging(repository.getBanks(), object : Listener<List<Bank>> {
            override fun onSuccess(data: List<Bank>?) {
                view?.showBanks(data ?: listOf())
            }
        })
    }

    override fun selectBank(bank: Bank) {
        view?.setResultSelectBank(bank)
    }

}