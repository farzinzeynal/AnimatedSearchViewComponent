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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


}