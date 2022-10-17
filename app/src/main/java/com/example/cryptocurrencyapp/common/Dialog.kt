package com.example.cryptocurrencyapp.common

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import com.example.cryptocurrencyapp.R

class Dialog {


    companion object {
        @JvmStatic
        fun show(title: String, message : String, context : Context, listener :((DialogInterface) -> Unit)) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)

            builder.setTitle(title).setMessage(message)
            builder.apply {
                setPositiveButton(context.getString(R.string.ok)) { dialog, id ->
                    listener(dialog)
                }
            }
            val dialog: AlertDialog? = builder.create()
            dialog?.show()
        }
    }
}