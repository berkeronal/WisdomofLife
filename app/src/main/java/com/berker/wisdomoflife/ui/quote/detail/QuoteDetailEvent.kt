package com.berker.wisdomoflife.ui.quote.detail

sealed class QuoteDetailEvent {

    object SaveNote : QuoteDetailEvent()
    data class WroteContent(val content: String) : QuoteDetailEvent()
    data class WroteAuthor(val author: String) : QuoteDetailEvent()
    data class ChangeBackgroundColor(val backgroundColorId: Int) : QuoteDetailEvent()
    data class ChangeBackgroundImageUrl(val imageUrl: String) : QuoteDetailEvent()
    data class ChangeBackgroundImageOpacity(val backgroundImageOpacity:Float) : QuoteDetailEvent()
    data class ChangeTextFont(val fontId: Int) : QuoteDetailEvent()
    data class ChangeTextSize(val textSize: Float) : QuoteDetailEvent()
    data class ChangeTextColor(val textColorId: Int) : QuoteDetailEvent()
    data class ChangeTextVerticalOrientation(val textVerticalOrientationId: Int) : QuoteDetailEvent()
    data class ChangeTextHorizontalOrientation(val textHorizontalOrientationId: Int) : QuoteDetailEvent()
    data class ChangeWeather(val weatherId:Int) : QuoteDetailEvent()
}