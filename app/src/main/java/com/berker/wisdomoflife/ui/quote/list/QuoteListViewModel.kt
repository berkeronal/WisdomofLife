package com.berker.wisdomoflife.ui.quote.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.common.Resource
import com.berker.wisdomoflife.domain.model.*
import com.berker.wisdomoflife.domain.usecase.QuoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteListViewModel @Inject constructor(
    private val quoteUseCases: QuoteUseCases,
) : ViewModel() {
    private val _quotesListState = MutableStateFlow(QuoteListState())
    val quoteListState: StateFlow<QuoteListState> = _quotesListState

    private var addQuotesJob: Job? = null

    init {
        addQuotes()
    }

    private fun addQuotes() {
        addQuotesJob?.cancel()
        addQuotesJob = viewModelScope.launch {
            quoteUseCases.addQuote(
                Quote(
                    "",
                    "",
                    "",
                    R.font.aclonica,
                    QuoteTextSize.MEDIUM,
                    "",
                    QuoteColor.AQUA,
                    QuoteVerticalOrientation.MIDDLE,
                    QuoteHorizontalOrientation.MIDDLE,
                )
            )
        }
    }

    fun getQuotes() {
        quoteUseCases.getQuotesUseCase()
            .onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _quotesListState.value = quoteListState.value.copy(
                        errorMessage = result.message ?: "Error",
                        isLoading = false
                    )
                }
                is Resource.Loading -> {
                    _quotesListState.value = quoteListState.value.copy(
                        errorMessage = "",
                        isLoading = true
                    )
                }
                is Resource.Success -> {
                    _quotesListState.value = quoteListState.value.copy(
                        quoteList = result.data ?: emptyList(),
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}