package com.example.cryptocurrencyapp.common

import android.text.SpannableStringBuilder
import androidx.core.text.bold

fun String.makePartTextBold(textBold: String): SpannableStringBuilder {
    return SpannableStringBuilder()
        .bold { append(textBold) }
        .append(this)
}