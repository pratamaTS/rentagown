package com.example.rentagown.v2.ui.fittingsize

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.model.Fitting
import com.example.rentagown.v2.data.source.BookingDataSource

class FittingSizePresenter(private val repository: BookingDataSource) : BaseRAGPresenter<FittingSizeContract.View>(), FittingSizeContract.Presenter {


    override fun loadOldFitting(fittingId: String) {
        safeCall(repository.getFitting(fittingId), object : Listener<Fitting> {
            override fun onSuccess(data: Fitting?) {
                data?.apply {
                    view?.setOldFittingData(this)
                }
            }
        }, RequestConfiguration(
            showErrorMessage = false,
            updateLoadingIndicator = false,
            updateLoadingContentIndicator = false))

    }

    override fun onBtnShowBodyTemplateClicked() {
        view?.showBodyTemplateDialog()
    }

    override fun onBtnSubmitFittingClicked() {
        val fitting = view?.getCurrentFittingValue()

        fitting?.transactionId?.let { _ ->
            view?.showLoading(true)

            safeCall(repository.saveFitting(fitting), object : Listener<Fitting> {
                override fun onSuccess(data: Fitting?) {
                    view?.setResultSaveFitting(data)
                }

            }, RequestConfiguration(updateLoadingContentIndicator = false))
        }

    }

}