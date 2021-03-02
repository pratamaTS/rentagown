package com.example.rentagown.v2.ui.confirmpayment

import com.example.rentagown.v2.base.BaseRAGPresenter
import com.example.rentagown.v2.data.enums.PaymentTypeEnum
import com.example.rentagown.v2.data.model.Booking
import com.example.rentagown.v2.data.model.ReqConfirm2ndPayment
import com.example.rentagown.v2.data.model.ReqConfirmPayment
import com.example.rentagown.v2.data.source.BookingDataSource
import com.example.rentagown.v2.util.Utils

class ConfirmPaymentPresenter(private val repository: BookingDataSource) : BaseRAGPresenter<ConfirmPaymentContract.View>(), ConfirmPaymentContract.Presenter {


    override fun start() {
        super.start()

        val booking = view?.getBookingData()
        if(booking == null) {
            view?.showMsgBookingNotFound()
            view?.setPaymentValue("-")

            return
        }

        view?.setPaymentValue(Utils.formatMoney(getConfirmPaymentValue(booking), "-", defaultIfZero = true))

    }

    override fun onBtnConfirmClicked() {

        val sourceBankName = view?.getBankName()
        val sourceAccountNumber = view?.getAccountNumber()
        val sourceAccountName = view?.getAccountName()
        val paymentAmountStr = view?.getPaymentAmount()

        if(sourceBankName.isNullOrBlank()) {
            view?.showMsgBankEmpty()
            return
        }

        if(sourceAccountNumber.isNullOrBlank()) {
            view?.showMsgAccountNumberEmpty()
            return
        }

        if(sourceAccountName.isNullOrBlank()) {
            view?.showMsgAccountNameEmpty()
            return
        }

        if(paymentAmountStr.isNullOrBlank()) {
            view?.showMsgPaymentAmountEmpty()
            return
        }

        // check if first or second payment
        val booking = view?.getBookingData()

        booking?.transactionId?.let { trxId ->
            view?.showLoading(true)

            // check wether 1st or 2nd payment
            val firstPaymentAmount = booking.paymentAmount ?: 0

            if(firstPaymentAmount <= 0) {
                // first payment
                val reqConfirmPayment = ReqConfirmPayment(
                        sourceBankName = sourceBankName,
                        sourceAccountNumber = sourceAccountNumber,
                        sourceAccountName = sourceAccountName,
                        paymentAmount = paymentAmountStr.toLongOrNull() ?: 0L
                )

                safeCall(repository.confirmPayment(trxId, reqConfirmPayment), object : Listener<Booking> {
                    override fun onSuccess(data: Booking?) {
                        data?.apply { view?.setResultPaymentSuccess(this) }
                    }

                }, RequestConfiguration(updateLoadingContentIndicator = false))
            } else {
                // second payment
                val reqConfirmPayment = ReqConfirm2ndPayment(
                        sourceBankName = sourceBankName,
                        sourceAccountNumber = sourceAccountNumber,
                        sourceAccountName = sourceAccountName,
                        paymentAmount = paymentAmountStr.toLongOrNull() ?: 0L
                )

                safeCall(repository.confirm2ndPayment(trxId, reqConfirmPayment), object : Listener<Booking> {
                    override fun onSuccess(data: Booking?) {
                        data?.apply { view?.setResultPaymentSuccess(this) }
                    }

                }, RequestConfiguration(updateLoadingContentIndicator = false))
            }
        }
    }

    // jangan dibuang dlu karena ini yang bener
//    private fun getConfirmPaymentValue(booking: Booking): Long {
//        PaymentTypeEnum.getByTypeId(booking.paymentMethod)?.let { paymentMethod ->
//            val remainingBill = booking.remainingBills ?: 0
//            val paymentAmount = booking.paymentAmount ?: 0
//            val downPayment = booking.downPayment ?: 0
//
//            return if(paymentMethod == PaymentTypeEnum.DOWN_PAYMENT) {
//                if(paymentAmount > 0 && paymentAmount == downPayment) {
//                    remainingBill
//                } else {
//                    downPayment
//                }
//            } else {
//                remainingBill
//            }
//        }
//
//        return 0
//    }

    private fun getConfirmPaymentValue(booking: Booking): Long {
        PaymentTypeEnum.getByTypeId(booking.paymentMethod)?.let { paymentMethod ->
            val remainingBill = booking.remainingBills ?: 0
            val paymentAmount = booking.paymentAmount ?: 0
            val downPayment = booking.downPayment ?: 0

            return if(paymentMethod == PaymentTypeEnum.DOWN_PAYMENT) {
                if(paymentAmount > 0 && paymentAmount == downPayment) {
                    remainingBill
                } else {
                    downPayment
                }
            } else {
                downPayment
            }
        }

        return 0
    }


}