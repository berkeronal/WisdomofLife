package com.berker.wisdomoflife.ui.quote.main

import android.view.View
import com.berker.wisdomoflife.domain.model.Quote

data class QuoteListState(
    val quoteList: List<Quote> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String = "",
){
    fun getLoadingViewVisibility() = if (isLoading) View.VISIBLE else View.GONE
}
