package com.example.rentagown.v2.ui.choosebank

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Bank

class ChooseBankContract {

    interface View : BaseView<Presenter> {

        fun showBanks(banks: List<Bank>)
        fun setResultSelectBank(bank: Bank)

    }

    interface Presenter: BasePresenter {

        fun loadBanks()
        fun selectBank(bank: Bank)
    }

}