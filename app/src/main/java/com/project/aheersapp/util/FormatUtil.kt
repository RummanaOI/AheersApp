package com.project.aheersapp.util

import android.util.Log
import java.text.DecimalFormat

object FormatUtil {

    fun roundOffDecimal(number: Double): String {
        val df = DecimalFormat("#,###.00")
        Log.i("VAL", df.format(number))
        return df.format(number)
    }
}