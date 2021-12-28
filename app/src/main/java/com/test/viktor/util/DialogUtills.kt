package com.test.viktor.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import androidx.appcompat.app.AlertDialog
import com.test.viktor.R

/**
 * Author Viktor Manev on 11/12/2020.
 */
object DialogUtills {


    fun createDialogConfirm(activity: Activity?, title: String, message: String?, buttonTitle: String, onClicked: (() -> Unit)?) {
        if (activity == null || activity.isFinishing)
            return

        createMessageDialog(
                activity,
                title = title,
                message = message,
                buttonTitle = buttonTitle,
                clickListener = DialogInterface.OnClickListener { _, _ ->
                    onClicked?.invoke()
                })
    }


    private fun createMessageDialog(
            context: Context, title: String,
            message: String?,
            buttonTitle: String,
            clickListener: DialogInterface.OnClickListener?
    ): AlertDialog {
        val builder = getAlertBuilder(context)

        if (!TextUtils.isEmpty(title))
            builder.setTitle(title)
        if (!TextUtils.isEmpty(message))
            builder.setMessage(message)

            builder.setPositiveButton(buttonTitle, clickListener)
        val dialog = builder.create()

        dialog.show()

        return dialog
    }

    private fun getAlertBuilder(context: Context): AlertDialog.Builder {
        return AlertDialog.Builder(context, R.style.MaterialAlertDialogStyle)
    }
}