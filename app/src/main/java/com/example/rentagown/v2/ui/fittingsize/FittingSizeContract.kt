package com.example.rentagown.v2.ui.fittingsize

import com.example.rentagown.v2.base.BasePresenter
import com.example.rentagown.v2.base.BaseView
import com.example.rentagown.v2.data.model.Fitting

interface FittingSizeContract {

    interface View: BaseView<Presenter> {

        fun showBodyTemplateDialog()
        fun setOldFittingData(fitting: Fitting)
        fun getSelectedTransactionId(): String?
        fun getSelectedFittingId(): String?
        fun getCurrentFittingValue(): Fitting
        fun setResultSaveFitting(fitting: Fitting?)

    }

    interface Presenter: BasePresenter {

        fun loadOldFitting(fittingId: String)
        fun onBtnShowBodyTemplateClicked()
        fun onBtnSubmitFittingClicked()

    }

}