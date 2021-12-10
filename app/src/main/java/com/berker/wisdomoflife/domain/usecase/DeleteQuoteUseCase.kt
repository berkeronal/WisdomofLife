package com.berker.wisdomoflife.domain.usecase

import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.repository.QuoteRepository

class DeleteQuoteUseCase(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(quote: Quote) {
        repository.deleteQuote(quote)
    }
}