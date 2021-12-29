package com.test.viktor.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.test.viktor.R
import com.test.viktor.util.DialogUtills
import kotlin.math.roundToInt

abstract class BaseActivity : AppCompatActivity() {

     fun View.animateResizeAndHide() {
        val animator = this.animate().alpha(0f).scaleX(0.7f).scaleY(0.7f)
        animator.duration = 200
        animator.start()
    }

     fun View.animateResizeAndShow() {
        val animator =
            this.animate().alpha(1f).scaleX(1f).scaleY(1f)
        animator.duration = 500
        animator.start()
    }

    fun View.animateFadeInSlow(){
        val animator = this.animate().alpha(1f)
        animator.duration = 1000
        animator.start()
    }

    protected fun Double.toText(): String {
        return this.roundToInt().toString()
    }

    protected  fun handleError(networkError: String?) {
        DialogUtills.createDialogConfirm(
            activity = this,
            title = "Error",
            message = networkError,
            buttonTitle = getString(R.string.ok),
            onClicked = null
        )
    }


}