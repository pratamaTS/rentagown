package com.example.rentagown.v2.ui.bodytemplate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.rentagown.R

class BodyTemplateDialog : DialogFragment() {

    companion object {
        const val TAG = "BodyTemplateDialog"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.dialog_body_template, container, false)

        return v
    }


}