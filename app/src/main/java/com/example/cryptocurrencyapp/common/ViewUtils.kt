package com.example.cryptocurrencyapp.common

import android.content.Context
import android.util.TypedValue
import android.view.View

fun View.visibility(state : Boolean) {
    visibility = if (state) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

fun Int.dpToPx(context: Context) : Float {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)
}