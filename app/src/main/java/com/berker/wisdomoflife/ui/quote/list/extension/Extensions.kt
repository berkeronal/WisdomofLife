package com.berker.wisdomoflife.ui.quote.list.extension

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.berker.wisdomoflife.R
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(this)
}

fun TextView.setFont(fontId: Int) {
    this.typeface = ResourcesCompat.getFont(this.context, fontId)
}