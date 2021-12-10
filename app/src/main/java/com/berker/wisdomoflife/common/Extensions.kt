package com.berker.wisdomoflife.common

import com.berker.wisdomoflife.domain.model.QuoteFonts


fun QuoteFonts.getNextFont(): QuoteFonts {
    val foundIndex = QuoteFonts.values().indexOf(this)
    return QuoteFonts.values()[(foundIndex + 1) % QuoteFonts.values().size]
}