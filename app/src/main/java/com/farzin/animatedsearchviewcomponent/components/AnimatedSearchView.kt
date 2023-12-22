package com.farzin.animatedsearchviewcomponent.components

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.farzin.animatedsearchviewcomponent.databinding.LayoutAnimatedSearchBinding

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

    fun requestInput(view: (EditText) -> Unit) {
        view(binding.edtSearch)
    }
}

