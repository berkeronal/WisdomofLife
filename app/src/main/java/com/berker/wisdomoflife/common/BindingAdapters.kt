package com.berker.wisdomoflife.common

import android.graphics.Typeface
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.berker.wisdomoflife.R
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView

@BindingAdapter("customFont")
fun EditText.changeFont(fontType: Typeface?) {
    fontType?.let {
        typeface = fontType
    }
}

@BindingAdapter("quoteBackgroundColor")
fun MaterialCardView.setQuoteBackgroundColor(color: Int) {
    setBackgroundColor(color)
}

@BindingAdapter("imageUrl")
fun ImageView.setQuoteImage(url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(this)
    }
}

@BindingAdapter("customTextSize")
fun TextView.setFontSize(textSize: Float) {
    this.textSize = textSize
}

@BindingAdapter("customTextFont")
fun TextView.setTextFont(font: Typeface?) {
    font?.let {
    this.typeface = font
    }
}

@BindingAdapter("customTextColor")
fun TextView.setQuoteTextColor(color: Int) {
    setTextColor(color)
}
