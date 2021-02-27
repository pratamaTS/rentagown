package com.example.rentagown.v2.ui.choosepaymenttype

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.fragment.app.FragmentManager
import com.example.rentagown.R
import com.example.rentagown.v2.data.enums.PaymentTypeEnum
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChoosePaymentTypeDialog : BottomSheetDialogFragment(), View.OnClickListener  {

    companion object {
        const val TAG = "ChoosePaymentTypeDialog"
    }

    interface Listener {
        fun onSelectedPaymentType(selectedPaymentType: PaymentTypeEnum)
    }


    private lateinit var rgPaymentType: RadioGroup
    private lateinit var btnContinue: Button
    private lateinit var btnClose: ImageButton

    private var listener: Listener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = layoutInflater.inflate(R.layout.dialog_choose_payment_type_v2, container, false)

        init()
        initWidget(v)
        return v
    }

    private fun init() {

    }

    private fun initWidget(v: View) {
        rgPaymentType = v.findViewById(R.id.rg_payment_type)
        btnContinue = v.findViewById(R.id.btn_continue)
        btnClose = v.findViewById(R.id.btn_close)

        btnClose.setOnClickListener(this)
        btnContinue.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()

        val selectedPaymentType = PaymentTypeEnum.getByOrdinal(arguments?.getInt("selected_payment_type", -1))

        if(selectedPaymentType != null) {
            rgPaymentType.check(if(selectedPaymentType == PaymentTypeEnum.DOWN_PAYMENT)
                R.id.rb_payment_type_down_payment else R.id.rb_payment_type_full)
        } else {
            rgPaymentType.clearCheck()
        }
    }

    fun show(manager: FragmentManager, tag: String?, listener: Listener? = null) {
        super.show(manager, tag)
        this.listener = listener
    }

    override fun onDismiss(dialog: DialogInterface) {
        this.listener = null
        super.onDismiss(dialog)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_close -> {
                dismiss()
            }
            R.id.btn_continue -> {
                when (rgPaymentType.checkedRadioButtonId) {
                    R.id.rb_payment_type_down_payment -> listener?.onSelectedPaymentType(PaymentTypeEnum.DOWN_PAYMENT)
                    R.id.rb_payment_type_full -> listener?.onSelectedPaymentType(PaymentTypeEnum.FULL_PAYMENT)
                }
                dismiss()
            }
        }
    }

}