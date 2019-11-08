package com.shajafamir.helloworld222

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.net.Uri.fromParts
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.net.Uri
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


const val EXTRA_MESSAGE = "com.shajafamir.helloworld222.MESSAGE"

@SuppressLint("ByteOrderMark")
class MainActivity : AppCompatActivity() {

    private val TAG = "PermissionDemo"
    private val CALL_REQUEST_CODE = 101
    private val requestRecieveSms = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.RECEIVE_SMS),
                requestRecieveSms)
        }
    }

    /** Called when the user taps the Send button */
    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
            val message = editText.text.toString()
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }

    /** Called when user taps lower button */
    fun dialContactPhone(view: View) {
        val editText = findViewById<EditText>(R.id.editText)
        val phoneNumber = editText.text.toString()
        //startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)))

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //val noPermIntent = Intent(this, DisplayNoPermissionsActivity::class.java).apply {}
            //startActivity(noPermIntent)
            Log.i(TAG, "Permission to record denied")
            ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.CALL_PHONE),  CALL_REQUEST_CODE)
        } else {
            val callIntent =
                Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null)).apply {}
            startActivity(callIntent)
        }
    }
}
