package com.berker.wisdomoflife.ui.quote.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.berker.wisdomoflife.common.*
import com.berker.wisdomoflife.domain.model.*
import com.berker.wisdomoflife.domain.usecase.QuoteUseCases
import com.berker.wisdomoflife.ui.quote.detail.util.QuoteDetailUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

    private val _quoteDetailUiEventFlow = MutableSharedFlow<QuoteDetailUiEvent>()
    val quoteDetailUiEventFlow = _quoteDetailUiEventFlow.asSharedFlow()

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
                if (!validateQuote()) {
                    throw IOException()
                }
                quoteUseCases.addQuote(_quoteDetailItemViewState.value.quote)
                _quoteDetailUiEventFlow.emit(QuoteDetailUiEvent.SaveQuote)
            } catch (e: IOException) {
                val error = e.localizedMessage
                _quoteDetailUiEventFlow.emit(QuoteDetailUiEvent.ShowErrorDialog(error ?: "Error"))
            }
        }
    }

    private fun validateQuote(): Boolean {
        //TODO()
        return true
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

    fun onTextColorChangeClicked() {
        val newTextColor = QuoteTextColor.values()
            .find { x -> x == _quoteDetailItemViewState.value.quote.textColor }?.getNextTextColor()

        newTextColor?.let {
            _quoteDetailItemViewState.value = quoteDetailItemViewState.value.copy(
                quote = quoteDetailItemViewState.value.quote.copy(
                    textColor = newTextColor
                )
            )
        }
    }

    fun onWeatherChangeClicked(){
        val newWeather = QuoteWeatherType.values().find { x-> x == _quoteDetailItemViewState.value.quote.weatherType}?.getNextWeather()

        newWeather?.let {
            _quoteDetailItemViewState.value = quoteDetailItemViewState.value.copy(
                quote = quoteDetailItemViewState.value.quote.copy(
                    weatherType = newWeather
                )
            )
        }
    }

    fun onBackgroundColorChangeClicked(){
        val newBackgroundColor = QuoteColor.values().find { x-> x == _quoteDetailItemViewState.value.quote.backgroundColor }?.getNextColor()

        newBackgroundColor?.let {
            _quoteDetailItemViewState.value = quoteDetailItemViewState.value.copy(
                quote = quoteDetailItemViewState.value.quote.copy(
                   backgroundColor = newBackgroundColor
                )
            )
        }
    }
}