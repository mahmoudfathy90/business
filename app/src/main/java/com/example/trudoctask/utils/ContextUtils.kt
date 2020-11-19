package com.example.trudoctask.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import java.util.*

object ContextUtils {

    fun Context.makeCall(mobileNo: String?) {
        val i = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$mobileNo"))
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        try {
            startActivity(i)
        } catch (e: ActivityNotFoundException) {
        }
    }


    fun Context.openUrlInBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    fun Context.goToMap(lat: Double, lon: Double) {
        val uri: String = java.lang.String.format(Locale.ENGLISH, "geo:%f,%f", lat, lon)
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }

    fun Context.share(text: String) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "True Doc recommendations")
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text)
        try {
            startActivity(sharingIntent)
        } catch (e: ActivityNotFoundException) {
        }
    }
}
