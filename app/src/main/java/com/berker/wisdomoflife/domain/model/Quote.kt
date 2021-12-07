package com.berker.wisdomoflife.domain.model

import androidx.annotation.FontRes
import com.berker.wisdomoflife.R

data class Quote(
    val name: String,
    val content: String,
    val author: String?,
    @FontRes
    val textFont: Int,
    val backgroundImageUrl: String,
    val backgroundColor: QuoteColor?,
    val textOrientation: QuoteOrientation,
)

enum class QuoteOrientation(val value: Int) {
    LEFT(0),
    MIDDLE(1),
    RIGHT(2),
}

enum class QuoteColor(val value: Int) {
    BLUE(R.color.note_blue),
    AQUA(R.color.note_aqua),
    MINT(R.color.note_mint),
    GREEN(R.color.note_green),
    ORANGE(R.color.note_orange),
    RED(R.color.note_red),
}

enum class QuoteFonts(val value: Int) {
    Aclonica(R.font.aclonica),
}


