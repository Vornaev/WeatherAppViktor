package com.test.viktor.util

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.text.TextUtils
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.test.viktor.R
import com.test.viktor.model.enums.UnitFormat

/**
 * Author Viktor Manev on 11/12/2020.
 */
object DialogUtills {


    fun createDialogConfirm(
        activity: Activity?,
        title: String,
        message: String?,
        buttonTitle: String,
        onClicked: (() -> Unit)?
    ) {
        if (activity == null || activity.isFinishing)
            return

        createMessageDialog(
            context = activity,
            title = title,
            message = message,
            buttonTitle = buttonTitle,
            clickListener = DialogInterface.OnClickListener { _, _ ->
                onClicked?.invoke()
            })
    }

    fun createSingleChoiceDialog(
        context: Context,
        items: List<String>,
        preselected: UnitFormat,
        onSelectedItem: (itemID: UnitFormat) -> Unit
    ) {

        var selectedItem = preselected.ordinal
        val builderDialog = getAlertBuilder(context)

        val arrayAdapter =
            ArrayAdapter<String>(context, R.layout.selecit_item_dialog)
        arrayAdapter.addAll(items)

        builderDialog.setTitle("Select Unit")
        builderDialog.setPositiveButton("OK") { dialog, which ->

            val unit = when (selectedItem) {
                0 -> UnitFormat.METRIC
                1 -> UnitFormat.IMPERIAL
                else -> UnitFormat.METRIC
            }
            onSelectedItem(unit)
        }

        builderDialog.setSingleChoiceItems(arrayAdapter, selectedItem) { dialog, which ->
            if (which in 1 downTo 0) {
                selectedItem = which
            }
        }

        builderDialog.setNegativeButton("Cancel") { _, _ -> { } }
        val alert = builderDialog.create()
        alert.show()
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