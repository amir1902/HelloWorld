package com.shajafamir.helloworld222

import android.content.Intent
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


const val EXTRA_MESSAGE = "com.shajafamir.helloworld222.MESSAGE"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
        startActivity(Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)))
    }
}
