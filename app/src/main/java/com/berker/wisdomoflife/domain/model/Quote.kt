package com.berker.wisdomoflife.domain.model

import androidx.annotation.FontRes
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.data.local.entity.QuoteEntity

data class Quote(
    val content: String,
    val author: String?,
    @FontRes
    val textFont: Int,
    val textSize: QuoteTextSize,
    val textColor: QuoteTextColor,
    val backgroundImageUrl: String,
    val backgroundColor: QuoteColor,
    val textVerticalOrientation: QuoteVerticalOrientation,
    val textHorizontalOrientation: QuoteHorizontalOrientation,
    var db_id: Int? = null,
    var animationId: Int? = null,
) {
    fun toQuoteEntity(): QuoteEntity {
        return QuoteEntity(
            content = content,
            author = author,
            textFont = textFont,
            textSize = textSize.value,
            textColor = textColor.value,
            backgroundImageUrl = backgroundImageUrl,
            backgroundColor = backgroundColor.value,
            textHorizontalOrientation = textHorizontalOrientation.value,
            textVerticalOrientation = textHorizontalOrientation.value,
        )
    }
}

enum class QuoteTextSize(val value: Float) {
    SMALL(20F),
    MEDIUM(30F),
    BIG(40F)
}

enum class QuoteVerticalOrientation(val value: Int) {
    UP(0),
    MIDDLE(1),
    DOWN(2),
}

enum class QuoteHorizontalOrientation(val value: Int) {
    LEFT(1),
    MIDDLE(4),
    RIGHT(3),
}

enum class QuoteWeatherType(val value: Int){
    SNOW(0),
    RAIN(1),
    AUTUMN(2)
}

enum class QuoteColor(val value: Int) {
    BLUE(R.color.quote_blue),
    AQUA(R.color.quote_aqua),
    MINT(R.color.quote_mint),
    GREEN(R.color.quote_green),
    ORANGE(R.color.quote_orange),
    RED(R.color.quote_red),
}

enum class QuoteTextColor(val value: Int) {
    BLUE(R.color.quote_blue),
    AQUA(R.color.quote_aqua),
    MINT(R.color.quote_mint),
    GREEN(R.color.quote_green),
    ORANGE(R.color.quote_orange),
    RED(R.color.quote_red),
}

enum class QuoteFonts(val value: Int) {
    Aclonica(R.font.aclonica),
}


