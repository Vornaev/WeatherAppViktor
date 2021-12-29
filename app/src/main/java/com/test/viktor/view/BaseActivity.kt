package com.test.viktor.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

     fun View.animateResizeAndHide() {
        val animator = this.animate().alpha(0f).scaleX(0.7f).scaleY(0.7f)
        animator.duration = 500
        animator.start()
    }

     fun View.animateResizeAndShow() {
        val animator =
            this.animate().alpha(1f).scaleX(1f).scaleY(1f)
        animator.duration = 300
        animator.start()
    }

}