package com.example.rentagown.Activity

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
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
import com.example.rentagown.Connection.Constants
import com.example.rentagown.Connection.Interface.EditProfileInterface
import com.example.rentagown.Connection.Interface.ProfileInterface
import com.example.rentagown.Connection.Interface.UploadPictInterface
import com.example.rentagown.Connection.Presenter.EditProfilePresenter
import com.example.rentagown.Connection.Presenter.ProfilePresenter
import com.example.rentagown.Connection.Presenter.UploadPictPresenter
import com.example.rentagown.Model.UpdateProfile
import com.example.rentagown.R
import com.example.rentagown.Response.EditProfile.DataEditProfile
import com.example.rentagown.Response.EditProfile.DataPict
import com.example.rentagown.Response.Profile.DataProfile
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.concurrent.TimeUnit


class EditProfileActivity : AppCompatActivity(), ProfileInterface, UploadPictInterface, EditProfileInterface, View.OnClickListener {
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
            window?.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT)
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
                btnUpload?.visibility = View.VISIBLE
                edtName?.isEnabled = true
                edtPhoneNumber?.isEnabled = true
                btnSave?.visibility = View.VISIBLE
            }
            R.id.edit_foto_profile -> {
                verifyStoragePermissions(this)
                launchGallery()
            }
            R.id.btn_save -> {
                btnUpload?.visibility = View.INVISIBLE
                edtName?.isEnabled = false
                edtPhoneNumber?.isEnabled = false
                btnSave?.visibility = View.GONE

                EditProfilePresenter(this).editProfile(this, UpdateProfile(edtPhoneNumber?.text.toString(), edtName?.text.toString(), ""))

                loadingDialog.show()
            }
            R.id.btn_add_photo -> uploadImage()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            uri = data?.data
            if (uri != null) {
                profilePict!!.setImageURI(uri)
                createImageData(uri!!)
            }
        }
    }

    private fun getProfile() {
        ProfilePresenter(this).getProfile(this)
    }

    private fun launchGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    private fun uploadImage() {
        //write the bytes in file
        val titlePic = String.format(
            name.toString(),
            System.currentTimeMillis()
        )

        val photo = createTempFile(myImageJPG!!)

        // create RequestBody instance from file
        val requestFile = RequestBody.create(
            MediaType.parse(contentResolver.getType(uri!!)),
            photo
        )

        // MultipartBody.Part is used to send also the actual file name
        val body = MultipartBody.Part.createFormData("avatar", photo!!.name, requestFile)

        UploadPictPresenter(this).uploadProfilePict(this, body)
    }

    private fun createImageData(uri: Uri) {
//        val inputStream = contentResolver.openInputStream(uri)
//        inputStream?.buffered()?.use {
//            imageData = it.readBytes()
//        }
        if (Build.VERSION.SDK_INT >= 30) {

            myImage = ImageDecoder.decodeBitmap(
                ImageDecoder.createSource(
                    contentResolver,
                    uri
                )
            )

        } else {

            contentResolver.openInputStream(uri)?.use { inputStream -> myImage = BitmapFactory.decodeStream(
                inputStream
            )
            }

        }

        myImageJPG = Bitmap.createBitmap(myImage!!.width, myImage!!.height, Bitmap.Config.ARGB_8888)
    }

    private fun createTempFile(bitmap: Bitmap): File? {
        val file = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            System.currentTimeMillis().toString() + "_image_profile.jpeg"
        )
        Log.d("file", file.toString())
        val fos = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)

        return file
    }

    override fun onSuccessGetProfile(dataProfile: DataProfile?) {
        edtName!!.setText(dataProfile?.name?.capitalize()?.trim())
        name = dataProfile?.name
        edtEmail!!.setText(dataProfile?.email)
        edtPhoneNumber!!.setText(dataProfile?.phone)

        if(dataProfile?.pathPhoto?.isNotEmpty() == true) {
            val imgURL: String = Constants.SERVER_URL + dataProfile?.pathPhoto
            Picasso.get().load(imgURL).into(profilePict)
        }else {
            profilePict?.setImageResource(R.drawable.family_1)
        }
    }

    override fun onErrorGetProfile(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG)
    }

    override fun onSuccessUploadPict(dataPict: ArrayList<DataPict>) {
        Toast.makeText(this, "Upload Success", Toast.LENGTH_LONG).show()
    }

    override fun onErrorGetUploadPict(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    @SuppressLint("AutoDispose")
    override fun onSuccessEditProfile(dataEditProfile: DataEditProfile) {
        edtName?.setText(dataEditProfile.name)
        edtPhoneNumber?.setText(dataEditProfile.phone)
        loadingDialog.dismiss()
        Toast.makeText(this, "Edit Profile Success", Toast.LENGTH_LONG).show()
    }

    override fun onErrorEditProfile(msg: String) {
        loadingDialog.dismiss()
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}