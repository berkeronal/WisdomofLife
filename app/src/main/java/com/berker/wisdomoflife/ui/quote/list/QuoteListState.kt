package com.berker.wisdomoflife.ui.quote.list

import com.berker.wisdomoflife.domain.model.Quote

data class QuoteListState(
    val quoteList: List<Quote> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String = "",
)
