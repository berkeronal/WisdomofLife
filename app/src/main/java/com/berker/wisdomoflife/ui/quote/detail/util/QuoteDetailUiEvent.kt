package com.berker.wisdomoflife.ui.quote.detail.util


sealed class QuoteDetailUiEvent {
    data class ShowErrorDialog(val message: String) : QuoteDetailUiEvent()
    object SaveQuote : QuoteDetailUiEvent()
}