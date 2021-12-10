package com.berker.wisdomoflife.ui.quote.detail

import android.view.View

data class QuoteDetailState(
    val isLoading: Boolean = true,
    val errorMessage: String = "",
    val optionMenuState: QuoteDetailOptionsState = QuoteDetailOptionsState.CLOSED
) {
    fun getLoadingViewVisibility() = if (isLoading) View.VISIBLE else View.GONE
}

enum class QuoteDetailOptionsState(val optionId: Int) {
    CLOSED(0),
    TEXT(1),
    BACKGROUND(2),
    WEATHER(3)
}