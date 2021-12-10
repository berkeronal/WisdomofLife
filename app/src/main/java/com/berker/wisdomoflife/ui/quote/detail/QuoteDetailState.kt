package com.berker.wisdomoflife.ui.quote.detail

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.model.QuoteFonts

data class QuoteDetailState(
    var quote: Quote? = null,
    val textFont: QuoteFonts = QuoteFonts.Abril
) {
    fun getFont(context: Context): Typeface? {
        return ResourcesCompat.getFont(context, textFont.fontResourceId)
    }
}