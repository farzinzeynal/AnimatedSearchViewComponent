package com.farzin.animatedsearchviewcomponent

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.ActionBar.LayoutParams
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.core.animation.doOnEnd
import androidx.core.view.marginStart
import com.farzin.animatedsearchviewcomponent.components.AnimatedSearchView

class MainActivity : AppCompatActivity() {


    lateinit var animatedSearchView: AnimatedSearchView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        animatedSearchView = findViewById(R.id.animatedSearchView)
        button = findViewById(R.id.button)

        button.setOnClickListener {
            expandSearchView()
        }



    }

    private fun expandSearchView() {
        val targetWidth = getScreenWidth() // Set target width to match_parent

        // Create a ValueAnimator for width animation
        val valueAnimator = ValueAnimator.ofInt(animatedSearchView.width, targetWidth)
        valueAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            val layoutParams = animatedSearchView.layoutParams
            layoutParams.width = animatedValue
            animatedSearchView.layoutParams = layoutParams
        }

        // Set animation duration
        valueAnimator.duration = 700 // Adjust duration as needed

        // Start the animation
        valueAnimator.start()

        valueAnimator.doOnEnd {
            showKeyboard(animatedSearchView)
        }
    }

    private fun collapsSearchView() {
        val targetWidth = 50 // Set target width to match_parent

        // Create a ValueAnimator for width animation
        val valueAnimator = ValueAnimator.ofInt(animatedSearchView.width, targetWidth)
        valueAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            val layoutParams = animatedSearchView.layoutParams
            layoutParams.width = animatedValue
            animatedSearchView.layoutParams = layoutParams
        }

        // Set animation duration
        valueAnimator.duration = 700 // Adjust duration as needed

        // Start the animation
        valueAnimator.start()

        valueAnimator.doOnEnd {

        }
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

    private fun getScreenWidth(): Int {
        return resources.displayMetrics.widthPixels - 100
    }

    private fun showKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }


}