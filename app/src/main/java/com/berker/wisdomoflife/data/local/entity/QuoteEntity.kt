package com.berker.wisdomoflife.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berker.wisdomoflife.domain.model.*

@Entity
data class QuoteEntity(
    val name: String,
    val content: String,
    val author: String?,
    val textFont: Int,
    val textSize: Int,
    val backgroundImageUrl: String,
    val backgroundColor: Int,
    val textVerticalOrientation: Int,
    val textHorizontalOrientation: Int,
    @PrimaryKey val id: Int? = null
) {
    fun toQuote(): Quote {
        return Quote(
            name = name,
            content = content,
            author = author,
            textFont = textFont,
            textSize = QuoteTextSize.values().find { x -> x.value == textSize }
                ?: QuoteTextSize.MEDIUM,
            backgroundImageUrl = backgroundImageUrl,
            backgroundColor = QuoteColor.values().find { x -> x.value == backgroundColor }
                ?: QuoteColor.AQUA,
            textVerticalOrientation = QuoteVerticalOrientation.values()
                .find { x -> x.value == textVerticalOrientation }
                ?: QuoteVerticalOrientation.MIDDLE,
            textHorizontalOrientation = QuoteHorizontalOrientation.values()
                .find { x -> x.value == textHorizontalOrientation }
                ?: QuoteHorizontalOrientation.MIDDLE,
            db_id = id
        )
    }
}
