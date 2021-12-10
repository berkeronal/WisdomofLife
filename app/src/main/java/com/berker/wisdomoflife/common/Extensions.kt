package com.berker.wisdomoflife.common

import com.berker.wisdomoflife.domain.model.*


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

fun QuoteWeatherType.getNextWeather(): QuoteWeatherType {
    return QuoteWeatherType.values()[(QuoteWeatherType.values()
        .indexOf(this) + 1) % QuoteWeatherType.values().size]
}

fun QuoteColor.getNextColor(): QuoteColor {
    return QuoteColor.values()[(QuoteColor.values().indexOf(this) + 1) % QuoteColor.values().size]
}