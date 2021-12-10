package com.berker.wisdomoflife.domain.usecase

import com.berker.wisdomoflife.common.Resource
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow

class GetQuoteUseCase(
    private val repository: QuoteRepository
) {

    operator fun invoke(quoteId: Int): Flow<Resource<Quote>> {
        return repository.getQuote(quoteId)
    }
}