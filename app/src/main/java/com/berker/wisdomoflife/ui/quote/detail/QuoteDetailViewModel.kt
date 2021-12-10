package com.berker.wisdomoflife.ui.quote.detail

import androidx.lifecycle.ViewModel
import com.berker.wisdomoflife.common.getNextFont
import com.berker.wisdomoflife.domain.model.QuoteFonts
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
    val quoteDetailState: StateFlow<QuoteDetailState> = _quoteDetailState

    fun restoreQuote(quoteId: Int) {

    }

    fun onEvent(event: QuoteDetailEvent) {
        when (event) {
            is QuoteDetailEvent.ChangeBackgroundColor -> TODO()
            is QuoteDetailEvent.ChangeBackgroundImageOpacity -> TODO()
            is QuoteDetailEvent.ChangeBackgroundImageUrl -> TODO()
            is QuoteDetailEvent.ChangeTextColor -> TODO()
            is QuoteDetailEvent.ChangeTextFont -> {
                val newTextFont =
                    _quoteDetailState.value.textFont.getNextFont()
                _quoteDetailState.value = quoteDetailState.value.copy(
                    textFont = newTextFont
                )
            }
            is QuoteDetailEvent.ChangeTextHorizontalOrientation -> TODO()
            is QuoteDetailEvent.ChangeTextSize -> TODO()
            is QuoteDetailEvent.ChangeTextVerticalOrientation -> TODO()
            is QuoteDetailEvent.ChangeWeather -> TODO()
            QuoteDetailEvent.SaveNote -> {

            }
            is QuoteDetailEvent.WroteAuthor -> TODO()
            is QuoteDetailEvent.WroteContent -> TODO()
        }
    }
}