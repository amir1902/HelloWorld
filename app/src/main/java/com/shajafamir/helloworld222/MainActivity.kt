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
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.content.Context


const val EXTRA_MESSAGE = "com.shajafamir.helloworld222.MESSAGE"

@SuppressLint("ByteOrderMark")
class MainActivity : AppCompatActivity() {

    private val TAG = "PermissionDemo"
    private val CALL_REQUEST_CODE = 101
    private val requestUserPermission = 2
    private val PREFS_NAME = "kotlincodes"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference:SharedPreference=SharedPreference(this)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ANSWER_PHONE_CALLS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.ANSWER_PHONE_CALLS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.CALL_PHONE
                ), requestUserPermission)
        }

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(
                    Manifest.permission.ANSWER_PHONE_CALLS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.CALL_PHONE
                ), requestUserPermission)
        }

    }
    /** Called when user taps lower button */
//    fun dialContactPhone(view: View) {
//        val editText = findViewById<EditText>(R.id.editText)
//        val phoneNumber = editText.text.toString()
//        //startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)))
//
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//            //val noPermIntent = Intent(this, DisplayNoPermissionsActivity::class.java).apply {}
//            //startActivity(noPermIntent)
//            Log.i(TAG, "Permission to record denied")
//            ActivityCompat.requestPermissions(this,  arrayOf(Manifest.permission.CALL_PHONE),  CALL_REQUEST_CODE)
//        } else {
//            val callIntent =
//                Intent(Intent.ACTION_CALL, Uri.fromParts("tel", phoneNumber, null)).apply {}
//            startActivity(callIntent)
//        }
//    }

    fun saveGateNumber(view: View) {
        val editText = findViewById<EditText>(R.id.editText4)
        val gateNumber = editText.text.toString()
        val sharedPreference:SharedPreference=SharedPreference(this)
        sharedPreference.save("gateNumber",gateNumber)
    }

    fun displayGateNumber(view: View) {
        val sharedPreference:SharedPreference=SharedPreference(this)
        val gateNumber = sharedPreference.getValueString("gateNumber")

        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.editText4)
        textView.text = gateNumber
        println(gateNumber)

        val sharedPreferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val sharedPreferenceIds = sharedPreferences.all.map { it.key } //returns List<String>
        println(sharedPreferenceIds[0])
    }

    fun approveGuest(view: View) {
        val editText1 = findViewById<EditText>(R.id.approvedName)
        val name = editText1.text.toString()
        val editText2 = findViewById<EditText>(R.id.approvedNumber)
        val number = editText2.text.toString()
        val sharedPreference: SharedPreference = SharedPreference(this)
        val sharedPreferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val sharedPreferenceIds = sharedPreferences.all.map { it.key } //returns List<String>

        if (name in sharedPreferenceIds) {
            sharedPreference.removeValue(name)
            sharedPreference.save(name, number)
        } else {
            sharedPreference.save(name, number)
        }

    }

    fun removeGuest(view: View) {
        val editText1 = findViewById<EditText>(R.id.approvedName)
        val name = editText1.text.toString()
        val editText2 = findViewById<EditText>(R.id.approvedNumber)
        val number = editText2.text.toString()
        val sharedPreference: SharedPreference = SharedPreference(this)
        val sharedPreferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val sharedPreferenceIds = sharedPreferences.all.map { it.key } //returns List<String>
        if (name in sharedPreferenceIds) {
            if(!(name == "gateNumber")){
                sharedPreference.removeValue(name)
            }
        }

    }

    fun displayGuestList(view: View) {
        val sharedPreference:SharedPreference=SharedPreference(this)
        val sharedPreferences = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val sharedPreferenceIds = sharedPreferences.all.map { it.key } //returns List<String>
        val sharedPreferenceVals = sharedPreferences.all.map { it.value } //returns List<String>
        var textToShow = ""
        for (i in 0..sharedPreferenceIds.size-1) {
            textToShow += sharedPreferenceIds[i]
            textToShow += " "
            textToShow += sharedPreference.getValueString(sharedPreferenceIds[i])
            textToShow += " \n"
        }
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.guestListView)
        textView.text = textToShow

    }
}
