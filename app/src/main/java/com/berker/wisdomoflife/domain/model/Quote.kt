package com.berker.wisdomoflife.domain.model

import androidx.annotation.FontRes
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.data.local.entity.QuoteEntity

data class Quote(
    val content: String,
    val author: String?,
    @FontRes
    val textFontId: Int,
    val textSize: QuoteTextSize,
    val textColor: QuoteTextColor,
    val weatherType: QuoteWeatherType,
    val backgroundImageUrl: String,
    val backgroundImageOpacity: QuoteBackgroundImageOpacity,
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
            textFont = textFontId,
            textSize = textSize.value,
            textColor = textColor.colorResourceId,
            weatherType = weatherType.value,
            backgroundImageUrl = backgroundImageUrl,
            backgroundImageOpacity = backgroundImageOpacity.value,
            backgroundColor = backgroundColor.colorResourceId,
            textHorizontalOrientation = textHorizontalOrientation.value,
            textVerticalOrientation = textHorizontalOrientation.value,
            id = db_id
        )
    }
}

enum class QuoteTextSize(val value: Float) {
    SMALL(20F),
    MEDIUM(30F),
    BIG(40F)
}

enum class QuoteBackgroundImageOpacity(val value: Float) {
    NONE(0F),
    LOW(0.3F),
    MID(0.5F),
    HIGH(0.8F),
    FULL(1F)
}

enum class QuoteVerticalOrientation(val value: Int) {
    UP(0),
    MIDDLE(1),
    DOWN(2),
}

enum class QuoteHorizontalOrientation(val value: Int) {
    LEFT(2),
    MIDDLE(4),
    RIGHT(6),
}

enum class QuoteWeatherType(val value: Int) {
    SNOW(0),
    RAIN(1),
    HEAVY_RAIN(2),
    AUTUMN(3),
    NONE(4),
}

enum class QuoteColor(val colorResourceId: Int) {
    BLUE(R.color.quote_blue),
    AQUA(R.color.quote_aqua),
    MINT(R.color.quote_mint),
    GREEN(R.color.quote_green),
    ORANGE(R.color.quote_orange),
    RED(R.color.quote_red),
    GREY(R.color.quote_grey)
}

enum class QuoteTextColor(val colorResourceId: Int) {
    BLUE(R.color.quote_blue),
    AQUA(R.color.quote_aqua),
    MINT(R.color.quote_mint),
    GREEN(R.color.quote_green),
    ORANGE(R.color.quote_orange),
    RED(R.color.quote_red),
    SOFT_WHITE(R.color.white_soft),
    WHITE(R.color.white),
    BLACK(R.color.black),
}

enum class QuoteFonts(val fontResourceId: Int) {
    Abril(R.font.abril_fatface),
    Aclonica(R.font.aclonica),
    Aguafina(R.font.aguafina_script),
    AlfaSlab(R.font.alfa_slab_one),
    Amatic(R.font.amatic_sc),
    Droid(R.font.droid_sans_mono),
    Eater(R.font.eater),
    FontDiner(R.font.fontdiner_swanky),
    Handlee(R.font.handlee),
    Monoton(R.font.monoton),
    Nosifer(R.font.nosifer)

}




