package com.berker.wisdomoflife.ui.quote.detail

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.berker.wisdomoflife.domain.model.*

data class QuoteDetailItemViewState(
    var quote: Quote = Quote(
        "",
        "",
        QuoteFonts.Abril.fontResourceId,
        QuoteTextSize.MEDIUM,
        QuoteTextColor.WHITE,
        QuoteWeatherType.NONE,
        "",
        QuoteBackgroundImageOpacity.MID,
        QuoteColor.GREY,
        QuoteVerticalOrientation.MIDDLE,
        QuoteHorizontalOrientation.MIDDLE
    ),
    val textFont: QuoteFonts = QuoteFonts.Abril
) {

    fun getRootImageViewVisibility() =
        if (quote.backgroundImageUrl.isNotEmpty()) View.VISIBLE else View.GONE

    fun getRootImageViewAlpha() = quote.backgroundImageOpacity.value
    fun getImageUrl() = quote.backgroundImageUrl
    fun getContentText() = quote.content
    fun getAuthorText() = quote.author
    fun getTextHorizontalAlignment() = quote.textHorizontalOrientation.value
    fun getTextVerticalAlignment() = quote.textVerticalOrientation.value
    fun getTextSize() = quote.textSize.value
    fun getTextColor(context: Context) =
        ContextCompat.getColor(context, quote.textColor.colorResourceId)

    fun getTextFont(context: Context) = ResourcesCompat.getFont(context, quote.textFontId)
    fun getBackgroundColor(context: Context): Int =
        ContextCompat.getColor(context, quote.backgroundColor.colorResourceId)
}