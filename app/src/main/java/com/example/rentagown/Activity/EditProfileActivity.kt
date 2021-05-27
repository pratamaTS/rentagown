package com.example.rentagown.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentManager
import butterknife.ButterKnife
import com.example.rentagown.Activity.ImagePickerActivity.Companion.showImagePickerOptions
import com.example.rentagown.Activity.ImagePickerActivity.PickerOptionListener
import com.example.rentagown.BuildConfig
import com.example.rentagown.Connection.Constants
import com.example.rentagown.Connection.Interface.EditProfileInterface
import com.example.rentagown.Connection.Interface.ProfileInterface
import com.example.rentagown.Connection.Interface.UploadPictInterface
import com.example.rentagown.Connection.Presenter.EditProfilePresenter
import com.example.rentagown.Connection.Presenter.ProfilePresenter
import com.example.rentagown.Connection.Presenter.UploadPictPresenter
import com.example.rentagown.Fragment.ProfileAfterFragment
import com.example.rentagown.Model.UpdateProfile
import com.example.rentagown.R
import com.example.rentagown.Response.EditProfile.DataEditProfile
import com.example.rentagown.Response.EditProfile.DataPict
import com.example.rentagown.Response.Profile.DataProfile
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class EditProfileActivity : AppCompatActivity(), ProfileInterface, UploadPictInterface, EditProfileInterface, View.OnClickListener {
    private val TAG = EditProfileActivity::class.java.simpleName
    val REQUEST_IMAGE = 100
    var back: ImageButton? = null
    var profilePict: CircleImageView? = null
    var btnUpload: Button? = null
    var edtName: EditText? = null
    var edtEmail: EditText? = null
    var edtPhoneNumber: EditText? = null
    var name: String? = null
    var uri: Uri? = null
    var myImage: Bitmap? = null
    var myImageJPG: Bitmap? = null
    private var imageData: ByteArray? = null
    private lateinit var btnEdit: Button
    private lateinit var btnSave: Button
    private lateinit var loadingDialog: AlertDialog
    private var canEdit: Boolean? = null
    private var pathPhoto: String? = null
    private var oldPathPhoto: String? = null

    companion object {

        private const val IMAGE_PICK_CODE = 999
        private const val REQUEST_EXTERNAL_STORAGE = 1
        private val PERMISSIONS_STORAGE = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        fun verifyStoragePermissions(activity: Activity?) {
            //Check if we have read permission
            val permission = ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (permission != PackageManager.PERMISSION_GRANTED) {
                //We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        ButterKnife.bind(this)
        ImagePickerActivity.clearCache(this)

        //INIT VIEW
        back = findViewById(R.id.im_back)
        profilePict = findViewById(R.id.edit_foto_profile)
        btnUpload = findViewById(R.id.btn_add_photo)
        edtName = findViewById(R.id.et_edit_name)
        edtEmail = findViewById(R.id.et_edit_email)
        edtPhoneNumber = findViewById(R.id.et_edit_phone)
        btnEdit = findViewById(R.id.btn_edit)
        btnSave = findViewById(R.id.btn_save)

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

        getProfile()

        //SET LISTENER
        profilePict!!.setOnClickListener(this)
        btnUpload!!.setOnClickListener(this)
        btnEdit.setOnClickListener(this)
        btnSave.setOnClickListener(this)
        back!!.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.im_back -> finish()
            R.id.btn_edit -> {
                edtName?.isEnabled = true
                edtPhoneNumber?.isEnabled = true
                btnSave.visibility = View.VISIBLE
                canEdit = true
            }
            R.id.edit_foto_profile -> {
                if (canEdit == true) {
                    Dexter.withActivity(this)
                        .withPermissions(
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        )
                        .withListener(object : MultiplePermissionsListener {
                            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                                if (report.areAllPermissionsGranted()) {
                                    showImagePickerOptions()
                                }
                                if (report.isAnyPermissionPermanentlyDenied) {
                                    showSettingsDialog()
                                }
                            }

                            override fun onPermissionRationaleShouldBeShown(
                                permissions: List<PermissionRequest?>?,
                                token: PermissionToken
                            ) {
                                token.continuePermissionRequest()
                            }
                        }).check()
                }
            }
            R.id.btn_save -> {
                btnUpload?.visibility = View.INVISIBLE
                edtName?.isEnabled = false
                edtPhoneNumber?.isEnabled = false
                btnSave.visibility = View.GONE

                if (pathPhoto == null) {
                    EditProfilePresenter(this).editProfile(
                        this, UpdateProfile(
                            edtPhoneNumber?.text.toString(),
                            edtName?.text.toString(),
                            edtEmail?.text.toString(),
                            ""
                        )
                    )
                } else {
                    EditProfilePresenter(this).editProfile(
                        this, UpdateProfile(
                            edtPhoneNumber?.text.toString(),
                            edtName?.text.toString(),
                            edtEmail?.text.toString(),
                            pathPhoto
                        )
                    )
                }

                loadingDialog.show()
            }
        }
    }

    private fun showImagePickerOptions() {
        showImagePickerOptions(this, object : PickerOptionListener {
            override fun onTakeCameraSelected() {
                launchCameraIntent()
            }

            override fun onChooseGallerySelected() {
                launchGalleryIntent()
            }
        })
    }

    private fun launchCameraIntent() {
        val intent = Intent(this, ImagePickerActivity::class.java)
        intent.putExtra(
            ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION,
            ImagePickerActivity.REQUEST_IMAGE_CAPTURE
        )

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true)
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000)
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000)
        startActivityForResult(intent, REQUEST_IMAGE)
    }

    private fun launchGalleryIntent() {
        val intent = Intent(this, ImagePickerActivity::class.java)
        intent.putExtra(
            ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION,
            ImagePickerActivity.REQUEST_GALLERY_IMAGE
        )

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true)
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1) // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1)
        startActivityForResult(intent, REQUEST_IMAGE)
    }

    private fun loadProfile(url: String) {
        Log.d(TAG, "Image cache path: $url")
        Picasso.get().load(url).into(profilePict)
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getString(R.string.dialog_permission_title))
        builder.setMessage(getString(R.string.dialog_permission_message))
        builder.setPositiveButton(getString(R.string.go_to_settings)) { dialog: DialogInterface, which: Int ->
            dialog.cancel()
            openSettings()
        }
        builder.setNegativeButton(
            getString(android.R.string.cancel)
        ) { dialog: DialogInterface, which: Int -> dialog.cancel() }
        builder.show()
    }

    // navigating user to app settings
    private fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri = Uri.fromParts("package", packageName, null)
        intent.data = uri
        startActivityForResult(intent, 101)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                val uri = data?.getParcelableExtra<Uri>("path")
                try {
                    // You can update this bitmap to your server
//                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                    val url = uri.toString()
                    Log.d("url", url)
                    val newUrl = url.replace("file:///", "/")

                    val sourceFile = File(newUrl)
                    val requestFile: RequestBody = RequestBody.create(
                        MediaType.parse("multipart/form-data"),
                        sourceFile
                    )

                    val body = MultipartBody.Part.createFormData(
                        "avatar",
                        sourceFile.name,
                        requestFile
                    )

                    val fullName = RequestBody.create(
                        MediaType.parse("multipart/form-data"),
                        "Your Name"
                    )

                    // loading profile image from local cache
                    loadProfile(uri.toString())

                    UploadPictPresenter(this).uploadProfilePict(this, body)
                    loadingDialog.show()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun getProfile() {
        ProfilePresenter(this).getProfile(this)
    }

    override fun onSuccessGetProfile(dataProfile: DataProfile?) {
        edtName!!.setText(dataProfile?.name?.capitalize()?.trim())
        name = dataProfile?.name
        edtEmail!!.setText(dataProfile?.email)
        edtPhoneNumber!!.setText(dataProfile?.phone)
        oldPathPhoto = dataProfile?.pathPhoto

        if(dataProfile?.pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = BuildConfig.BASE_PHOTO_URL + dataProfile?.pathPhoto

            Picasso.get().load(imgURL).into(profilePict)
        }else {
            profilePict?.setImageResource(R.drawable.bg_placholder_edit_profile)
        }
    }

    override fun onErrorGetProfile(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG)
    }

    override fun onSuccessUploadPict(dataPict: ArrayList<DataPict>) {
        for (data in dataPict){
            pathPhoto = data.pathPhoto
        }
        loadingDialog.dismiss()
        Toast.makeText(this, "Upload Success", Toast.LENGTH_LONG).show()
    }

    override fun onErrorGetUploadPict(msg: String) {
        loadingDialog.dismiss()
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    @SuppressLint("AutoDispose")
    override fun onSuccessEditProfile(dataEditProfile: DataEditProfile) {
        edtName?.setText(dataEditProfile.name)
        edtPhoneNumber?.setText(dataEditProfile.phone)
        loadingDialog.dismiss()
        val imgURL: String = BuildConfig.BASE_PHOTO_URL + dataEditProfile.pathPhoto

        if(dataEditProfile?.pathPhoto?.isNotEmpty() == true || oldPathPhoto?.isNotEmpty() == true) {
            val imgURL: String = BuildConfig.BASE_PHOTO_URL + dataEditProfile?.pathPhoto
            Picasso.get().load(imgURL).into(profilePict)
        }else {
            profilePict?.setImageResource(R.drawable.bg_placholder_edit_profile)
        }

        setResultOk(dataEditProfile.name.toString(), dataEditProfile.pathPhoto.toString())
        Toast.makeText(this, "Edit Profile Success", Toast.LENGTH_LONG).show()
    }

    override fun onErrorEditProfile(msg: String) {
        loadingDialog.dismiss()
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    private fun setResultOk(name: String, path: String) {
//        val fragm = ProfileAfterFragment()
//        fragm.tvName?.text = name.capitalize().trim()
//
//        if(path.isNotEmpty() == true) {
//            val imgURL: String = Constants.SERVER_URL + path
//            Picasso.get().load(imgURL).into(fragm.imProfile)
//        }else {
//            fragm.imProfile?.setImageResource(R.drawable.bg_placeholder)
//        }
//        val mainAfterActivity =
//            Intent(this, MainAfterActivity::class.java)
//        intent.putExtra("nav_id", 2)
//        startActivity(mainAfterActivity)
//        finish()
    }
}