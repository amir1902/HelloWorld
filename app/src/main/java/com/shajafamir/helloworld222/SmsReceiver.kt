package com.shajafamir.helloworld222


import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.telephony.SmsMessage
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity
import android.R.attr.phoneNumber
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


class SmsReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        val extras = intent.extras
        var phoneNumber = ""
        var messageText = ""

        if(extras != null){
            val sms = extras.get("pdus") as Array<Any>
            for(i in sms.indices){
                val format = extras.getString("format")
                var smsMessage = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    SmsMessage.createFromPdu(sms[i] as ByteArray,format)
                }else{
                    SmsMessage.createFromPdu(sms[i] as ByteArray)
                }
                phoneNumber = smsMessage.originatingAddress.toString()
                messageText = smsMessage.messageBody.toString()
                Toast.makeText(
                    context,
                    "phoneNumber: $phoneNumber\n" +
                            "messageText: $messageText",
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:$messageText")
                callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(callIntent)
            }
        }
    }
}