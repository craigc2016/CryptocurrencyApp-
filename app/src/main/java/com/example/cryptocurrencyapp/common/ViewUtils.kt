package com.example.cryptocurrencyapp.common

import android.view.View

fun View.visibility(state : Boolean) {
    visibility = if (state) {
        View.VISIBLE
    } else {
        View.GONE
    }
}