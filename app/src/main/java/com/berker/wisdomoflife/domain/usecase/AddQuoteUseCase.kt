package com.berker.wisdomoflife.domain.usecase

import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.repository.QuoteRepository

class AddQuoteUseCase(
    private val repository: QuoteRepository
) {
    suspend operator fun invoke(newQuote: Quote) {
        repository.insertQuote(newQuote)
    }
}