package com.berker.wisdomoflife.ui.quote.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berker.wisdomoflife.common.*
import com.berker.wisdomoflife.domain.model.QuoteFonts
import com.berker.wisdomoflife.domain.model.QuoteHorizontalOrientation
import com.berker.wisdomoflife.domain.model.QuoteTextColor
import com.berker.wisdomoflife.domain.model.QuoteTextSize
import com.berker.wisdomoflife.domain.usecase.QuoteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class QuoteDetailViewModel @Inject constructor(
    private val quoteUseCases: QuoteUseCases,
) : ViewModel() {
    private val _quoteDetailState = MutableStateFlow(QuoteDetailState())
    val quoteDetailState: StateFlow<QuoteDetailState> = _quoteDetailState

    private val _quoteDetailItemViewState = MutableStateFlow(QuoteDetailItemViewState())
    val quoteDetailItemViewState: StateFlow<QuoteDetailItemViewState> = _quoteDetailItemViewState

    fun restoreQuote(quoteId: Int) {
        viewModelScope.launch {
            quoteUseCases.getQuoteUseCase(quoteId).collectLatest { result ->
                when (result) {
                    is Resource.Error -> {

                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        if (result.data != null) {
                            _quoteDetailItemViewState.value = quoteDetailItemViewState.value.copy(
                                quote = result.data,
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: QuoteDetailEvent) {
        when (event) {
            is QuoteDetailEvent.ChangeBackgroundColor -> TODO()
            is QuoteDetailEvent.ChangeBackgroundImageOpacity -> TODO()
            is QuoteDetailEvent.ChangeBackgroundImageUrl -> TODO()
            is QuoteDetailEvent.ChangeTextColor -> TODO()
            is QuoteDetailEvent.ChangeTextFont -> {

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

    fun onWroteContent(newContentString: String) {
        _quoteDetailItemViewState.value.quote = quoteDetailItemViewState.value.quote.copy(
            content = newContentString,
        )
    }

    fun onWroteAuthor(newAuthorString: String) {
        _quoteDetailItemViewState.value.quote = quoteDetailItemViewState.value.quote.copy(
            author = newAuthorString,
        )
    }

    fun onSaveQuote() {
        viewModelScope.launch {
            try {
                quoteUseCases.addQuote(_quoteDetailItemViewState.value.quote)
            } catch (e: IOException) {
                val error = e.localizedMessage
            }
        }
    }

    fun onFontChangeClicked() {
        val newTextFont = QuoteFonts.values()
            .find { x -> x.fontResourceId == _quoteDetailItemViewState.value.quote.textFontId }
            ?.getNextFont()

        newTextFont?.let {
            _quoteDetailItemViewState.value = quoteDetailItemViewState.value.copy(
                textFont = newTextFont,
                quote = quoteDetailItemViewState.value.quote.copy(
                    textFontId = newTextFont.fontResourceId
                )
            )
        }
    }

    fun onTextSizeChangeClicked() {
        val newTextSize =
            QuoteTextSize.values().find { x -> x == _quoteDetailItemViewState.value.quote.textSize }
                ?.getNextTextSize()

        newTextSize?.let {
            _quoteDetailItemViewState.value = quoteDetailItemViewState.value.copy(
                quote = quoteDetailItemViewState.value.quote.copy(
                    textSize = newTextSize
                )
            )
        }
    }

    fun onTextHorizontalAlignmentChangeClicked() {
        val newHorizontalOrientation =
            QuoteHorizontalOrientation.values()
                .find { x -> x == _quoteDetailItemViewState.value.quote.textHorizontalOrientation }
                ?.getNextAlignment()

        newHorizontalOrientation?.let {
            _quoteDetailItemViewState.value = quoteDetailItemViewState.value.copy(
                quote = quoteDetailItemViewState.value.quote.copy(
                    textHorizontalOrientation = newHorizontalOrientation
                )
            )
        }
    }

    fun onTextColorChangeClicked(){
        val newTextColor= QuoteTextColor.values().find { x-> x == _quoteDetailItemViewState.value.quote.textColor }?.getNextTextColor()

        newTextColor?.let {
            _quoteDetailItemViewState.value = quoteDetailItemViewState.value.copy(
                quote = quoteDetailItemViewState.value.quote.copy(
                    textColor = newTextColor
                )
            )
        }
    }
}