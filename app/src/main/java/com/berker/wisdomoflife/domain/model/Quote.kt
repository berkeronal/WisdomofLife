package com.berker.wisdomoflife.domain.model

import androidx.annotation.FontRes
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.data.local.entity.QuoteEntity

data class Quote(
    val name: String,
    val content: String,
    val author: String?,
    @FontRes
    val textFont: Int,
    val textSize: QuoteTextSize,
    val backgroundImageUrl: String,
    val backgroundColor: QuoteColor,
    val textVerticalOrientation: QuoteVerticalOrientation,
    val textHorizontalOrientation: QuoteHorizontalOrientation,
    var db_id: Int? = null
) {
    fun toQuoteEntity(): QuoteEntity {
        return QuoteEntity(
            name = name,
            content = content,
            author = author,
            textFont = textFont,
            textSize = textSize.value,
            backgroundImageUrl = backgroundImageUrl,
            backgroundColor = backgroundColor.value,
            textHorizontalOrientation = textHorizontalOrientation.value,
            textVerticalOrientation = textHorizontalOrientation.value,
        )
    }
}

enum class QuoteTextSize(val value: Int) {
    SMALL(11),
    MEDIUM(30),
    BIG(60)
}

enum class QuoteVerticalOrientation(val value: Int) {
    UP(0),
    MIDDLE(1),
    DOWN(2),
}

enum class QuoteHorizontalOrientation(val value: Int) {
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


