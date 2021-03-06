package com.berker.wisdomoflife.domain.usecase

data class QuoteUseCases(
    val getQuotesUseCase: GetQuotesUseCase,
    val addQuote:AddQuoteUseCase,
    val getQuoteUseCase: GetQuoteUseCase,
    val deleteQuoteUseCase: DeleteQuoteUseCase
)
