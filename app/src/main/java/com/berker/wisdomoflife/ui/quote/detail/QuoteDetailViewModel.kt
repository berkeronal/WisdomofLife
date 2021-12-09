package com.berker.wisdomoflife.ui.quote.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.berker.wisdomoflife.domain.usecase.QuoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(
    private val quoteUseCases: QuoteUseCases,
) : ViewModel() {
    private val _quoteDetailState = MutableStateFlow(QuoteDetailState())
    private val quoteDetailState: StateFlow<QuoteDetailState> = _quoteDetailState

    fun restoreQuote(quoteId:Int) {

    }
}