package com.berker.wisdomoflife.data.local.entity

import androidx.annotation.FontRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.model.QuoteColor
import com.berker.wisdomoflife.domain.model.QuoteOrientation

@Entity
data class QuoteEntity(
    val name: String,
    val content: String,
    val author: String?,
    @FontRes
    val textFont: Int,
    val backgroundImageUrl: String,
    val backgroundColor: QuoteColor?,
    val textOrientation: QuoteOrientation,
    @PrimaryKey val id: Int? = null
) {
    fun toQuote(): Quote {
        return Quote(
            name = name,
            content = content,
            author = author,
            textFont = textFont,
            backgroundImageUrl = backgroundImageUrl,
            backgroundColor = backgroundColor,
            textOrientation = textOrientation
        )
    }
}
