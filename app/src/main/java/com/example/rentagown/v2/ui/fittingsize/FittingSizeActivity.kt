package com.example.rentagown.v2.ui.fittingsize

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.lifecycle.Lifecycle
import com.example.rentagown.R
import com.example.rentagown.v2.base.BaseRAGActivity
import com.example.rentagown.v2.data.model.Fitting
import com.example.rentagown.v2.data.network.RAGApi
import com.example.rentagown.v2.data.remote.RemoteRepositoryLocator
import com.example.rentagown.v2.data.repository.RepositoryLocator
import com.example.rentagown.v2.ui.bodytemplate.BodyTemplateDialog


class FittingSizeActivity : BaseRAGActivity<FittingSizeContract.Presenter>(), FittingSizeContract.View,
        View.OnClickListener {

    companion object {

        const val REQ_EDIT_FITTING = 3551
        const val RES_FITTING_SAVED = 3550

    }

    override val layoutId: Int = R.layout.activity_fitting_size_v2
    override var btnBackId: Int = R.id.btn_back

    override lateinit var presenter: FittingSizeContract.Presenter

    private lateinit var bodyTemplateDialog: BodyTemplateDialog

    private lateinit var btnShowBodyTemplate: CardView
    private lateinit var btnSubmitFitting: Button

    private lateinit var etBustSize: EditText
    private lateinit var etArmHoleSize: EditText
    private lateinit var etWaistSize: EditText
    private lateinit var etHipSize: EditText


    override fun init() {
        super.init()

        presenter = FittingSizePresenter(
            RepositoryLocator
                .getInstance(
                    RemoteRepositoryLocator
                        .getInstance(RAGApi.apiService(this))
                )
                .bookingRepository
        )
    }

    override fun setupWidgets() {
        super.setupWidgets()

        btnShowBodyTemplate = findViewById(R.id.btn_show_body_template)
        btnSubmitFitting = findViewById(R.id.btn_submit_fitting)

        etBustSize = findViewById(R.id.et_bust_size)
        etArmHoleSize = findViewById(R.id.et_arm_hole_size)
        etWaistSize = findViewById(R.id.et_waist_size)
        etHipSize = findViewById(R.id.et_hip_size)

        btnShowBodyTemplate.setOnClickListener(this)
        btnSubmitFitting.setOnClickListener(this)

        if(!this::bodyTemplateDialog.isInitialized) {
            bodyTemplateDialog = BodyTemplateDialog()
        }
    }

    override fun setupAdapter() {
        super.setupAdapter()


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSelectedFittingId()?.let { fittingId ->
            presenter.loadOldFitting(fittingId)
        }
    }

    override fun showBodyTemplateDialog() {
        if(lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED)) {
            bodyTemplateDialog.show(supportFragmentManager, BodyTemplateDialog.TAG)
        }
    }

    override fun setOldFittingData(fitting: Fitting) {
        if(fitting.bustSize != null) etBustSize.setText(fitting.bustSize.toString())
        if(fitting.armHoleSize != null) etArmHoleSize.setText(fitting.armHoleSize.toString())
        if(fitting.waistSize != null) etWaistSize.setText(fitting.waistSize.toString())
        if(fitting.hipSize != null) etHipSize.setText(fitting.hipSize.toString())
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_show_body_template -> presenter.onBtnShowBodyTemplateClicked()
            R.id.btn_submit_fitting -> {
                if(etBustSize.text.length == 0){
                    Toast.makeText(this, "Bust size cannot be empty", Toast.LENGTH_SHORT).show()
                }else if(etArmHoleSize.text.length == 0){
                    Toast.makeText(this, "Arm hole size cannot be empty", Toast.LENGTH_SHORT).show()
                }else if(etWaistSize.text.length == 0){
                    Toast.makeText(this, "Waist size cannot be empty", Toast.LENGTH_SHORT).show()
                }else if(etHipSize.text.length == 0){
                    Toast.makeText(this, "Hip size cannot be empty", Toast.LENGTH_SHORT).show()
                }else{
                    presenter.onBtnSubmitFittingClicked()
                }
            }
        }
    }

    override fun getSelectedTransactionId(): String? = intent.getStringExtra("transaction_id")
    override fun getSelectedFittingId(): String? = intent.getStringExtra("fitting_id")

    override fun getCurrentFittingValue(): Fitting {
        val bustSize = etBustSize.text.toString().trim()
        val armHoleSize = etArmHoleSize.text.toString().trim()
        val waistSize = etWaistSize.text.toString().trim()
        val hipSize = etHipSize.text.toString().trim()

        return Fitting(transactionId = getSelectedTransactionId(),
            fittingId = getSelectedFittingId(),
            bustSize = bustSize.toIntOrNull(),
            armHoleSize = armHoleSize.toIntOrNull(),
            waistSize = waistSize.toIntOrNull(),
            hipSize = hipSize.toIntOrNull()
        )

    }

    override fun setResultSaveFitting(fitting: Fitting?) {
        Intent().apply {
            putExtra("transaction_id", fitting?.transactionId)
            putExtra("fitting_id", fitting?.fittingId)

            setResult(RES_FITTING_SAVED, this)
            finish()
        }
    }

    override fun onPause() {
        if(bodyTemplateDialog.isVisible) {
            bodyTemplateDialog.dismiss()
        }
        super.onPause()
    }

    //    class DecimalDigitsInputFilter : InputFilter {
//        var mPattern: Pattern = Pattern.compile("^(\\d+)?([.]?\\d?)?\$")
//        override fun filter(
//            source: CharSequence,
//            start: Int,
//            end: Int,
//            dest: Spanned,
//            dstart: Int,
//            dend: Int
//        ): CharSequence? {
//            val matcher: Matcher = mPattern.matcher(dest)
//            return if (!matcher.matches()) "" else null
//        }
//    }

}