package com.berker.wisdomoflife.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berker.wisdomoflife.domain.model.*

@Entity
data class QuoteEntity(
    val content: String,
    val author: String?,
    val textFont: Int,
    val textSize: Float,
    val textColor: Int,
    val weatherType: Int,
    val backgroundImageUrl: String,
    val backgroundImageOpacity: Float,
    val backgroundColor: Int,
    val textVerticalOrientation: Int,
    val textHorizontalOrientation: Int,
    @PrimaryKey val id: Int? = null,
    val animationId: Int? = null,
) {
    fun toQuote(): Quote {
        return Quote(
            content = content,
            author = author,
            textFontId = textFont,
            textSize = QuoteTextSize.values().find { x -> x.value == textSize }
                ?: QuoteTextSize.MEDIUM,
            textColor = QuoteTextColor.values().find { x -> x.colorResourceId == textColor }
                ?: QuoteTextColor.RED,
            weatherType = QuoteWeatherType.values().find { x -> x.value == weatherType }
                ?: QuoteWeatherType.NONE,
            backgroundImageUrl = backgroundImageUrl,
            backgroundImageOpacity = QuoteBackgroundImageOpacity.values()
                .find { x -> x.value == backgroundImageOpacity } ?: QuoteBackgroundImageOpacity.LOW,
            backgroundColor = QuoteColor . values ().find { x -> x.colorResourceId == backgroundColor }
            ?: QuoteColor.AQUA,
            textVerticalOrientation = QuoteVerticalOrientation.values()
                .find { x -> x.value == textVerticalOrientation }
                ?: QuoteVerticalOrientation.MIDDLE,
            textHorizontalOrientation = QuoteHorizontalOrientation.values()
                .find { x -> x.value == textHorizontalOrientation }
                ?: QuoteHorizontalOrientation.MIDDLE,
            db_id = id,
            animationId = animationId,
        )
    }
}
