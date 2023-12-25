package com.farzin.animatedsearchviewcomponent.components

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import com.farzin.animatedsearchviewcomponent.databinding.LayoutAnimatedSearchBinding
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.google.android.material.internal.ViewUtils.showKeyboard
import kotlin.math.roundToInt

class AnimatedSearchView @JvmOverloads constructor(
    ctx: Context,
    attrSet: AttributeSet? = null,
    styleDef: Int = 0
) : LinearLayout(ctx, attrSet, styleDef) {

    private val binding: LayoutAnimatedSearchBinding =
        LayoutAnimatedSearchBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        binding.imgClose.setOnClickListener {
            binding.edtSearch.setText("")
            collapsSearchView()
        }

        binding.imgSearchButton.setOnClickListener {
            expandSearchView()
        }

        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.imgClose.isVisible = s.toString().isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }


    @SuppressLint("RestrictedApi")
    private fun expandSearchView() {
        binding.imgSearchButton.isVisible=false
        binding.searchLayout.isVisible = true

        val targetWidth = getScreenWidth()

        val valueAnimator = ValueAnimator.ofInt(binding.searchLayout.width, targetWidth)
        valueAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            val layoutParams = binding.searchLayout.layoutParams
            layoutParams.width = animatedValue
            binding.searchLayout.layoutParams = layoutParams
        }

        valueAnimator.duration = 300

        valueAnimator.start()

        valueAnimator.doOnEnd {
            binding.edtSearch.requestFocus()
            showKeyboard(binding.root)
        }
    }

    @SuppressLint("RestrictedApi")
    private fun collapsSearchView() {
        binding.imgSearchButton.isVisible=true
        binding.searchLayout.isVisible = false

        val targetWidth = 50

        val valueAnimator = ValueAnimator.ofInt(binding.searchLayout.width, targetWidth)
        valueAnimator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int
            val layoutParams = binding.searchLayout.layoutParams
            layoutParams.width = animatedValue
            binding.searchLayout.layoutParams = layoutParams
        }

        valueAnimator.duration = 300

        valueAnimator.start()

        valueAnimator.doOnEnd {
            hideKeyboard(binding.root)
        }
    }

    private fun dpToPx(dp: Int): Int {
        val density = resources.displayMetrics.density
        return (dp * density).toInt()
    }

    private fun getScreenWidth(): Int {
        return resources.displayMetrics.widthPixels.toDouble().times( 0.85).roundToInt()
    }


    fun requestInput(view: (EditText) -> Unit) {
        view(binding.edtSearch)
    }
}

