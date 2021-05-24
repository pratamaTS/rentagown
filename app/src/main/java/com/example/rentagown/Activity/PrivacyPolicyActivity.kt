package com.example.rentagown.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.webkit.WebView
import android.widget.CompoundButton
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.rentagown.Connection.Run
import com.example.rentagown.R

class PrivacyPolicyActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var webView: WebView
    private lateinit var back: ImageButton
    private lateinit var swipe: SwipeRefreshLayout
    private lateinit var loadingDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        swipe = findViewById(R.id.swipe_refresh_privacy_policy)

        if(!this::loadingDialog.isInitialized) {
            loadingDialog = AlertDialog.Builder(this)
                .setView(R.layout.layout_loading)
                .create()
            loadingDialog.setCanceledOnTouchOutside(false)

            val window = loadingDialog.window
            window?.setLayout(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            window?.setGravity(Gravity.CENTER)
        }

        swipe.setOnRefreshListener {
            webView.reload()
            Run.after(2000, {
                swipe.isRefreshing = false
            })
        }

        LoadWeb()

        //SET LISTENER
        back.setOnClickListener(this)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun LoadWeb() {
        loadingDialog.show()
        webView = findViewById(R.id.webview_privacy_policy)
        webView.getSettings().javaScriptEnabled = true
        webView.getSettings().setAppCacheEnabled(true)
        webView.loadUrl("https://rentagown.id/privacy-policy/")
        Run.after(3000, {
            loadingDialog.dismiss()
        })
    }
}