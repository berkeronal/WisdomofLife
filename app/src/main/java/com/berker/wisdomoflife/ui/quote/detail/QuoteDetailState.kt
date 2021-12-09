package com.berker.wisdomoflife.ui.quote.detail

import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.domain.model.*

data class QuoteDetailState(
    var quote: Quote = Quote(
        "",
        "Mustafa Kemal Atatürk",
        R.font.handlee,
        QuoteTextSize.SMALL,
        QuoteTextColor.SOFT_WHITE,
        QuoteWeatherType.HEAVY_RAIN,
        "",
        QuoteBackgroundImageOpacity.LOW,
        QuoteColor.GREY,
        QuoteVerticalOrientation.MIDDLE,
        QuoteHorizontalOrientation.MIDDLE,
    ),

    )