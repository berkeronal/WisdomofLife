package com.berker.wisdomoflife.ui.quote.main.util

sealed class QuoteListUiEvent {
    data class ShowErrorDialog(val message: String) : QuoteListUiEvent()
    object AddQuote : QuoteListUiEvent()
}