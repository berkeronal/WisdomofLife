package com.berker.wisdomoflife.common

import com.berker.wisdomoflife.domain.model.QuoteFonts
import com.berker.wisdomoflife.domain.model.QuoteHorizontalOrientation
import com.berker.wisdomoflife.domain.model.QuoteTextColor
import com.berker.wisdomoflife.domain.model.QuoteTextSize


fun QuoteFonts.getNextFont(): QuoteFonts {
    val foundIndex = QuoteFonts.values().indexOf(this)
    return QuoteFonts.values()[(foundIndex + 1) % QuoteFonts.values().size]
}

fun QuoteTextSize.getNextTextSize(): QuoteTextSize {
    return QuoteTextSize.values()[(QuoteTextSize.values()
        .indexOf(this) + 1) % QuoteTextSize.values().size]
}

fun QuoteHorizontalOrientation.getNextAlignment(): QuoteHorizontalOrientation {
    return QuoteHorizontalOrientation.values()[(QuoteHorizontalOrientation.values()
        .indexOf(this) + 1) % QuoteHorizontalOrientation.values().size]
}

fun QuoteTextColor.getNextTextColor(): QuoteTextColor {
    return QuoteTextColor.values()[(QuoteTextColor.values()
        .indexOf(this) + 1) % QuoteTextColor.values().size]
}