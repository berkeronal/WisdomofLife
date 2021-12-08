package com.berker.wisdomoflife.domain.usecase

import com.berker.wisdomoflife.common.Resource
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow

class GetQuotesUseCase(
    private val repository: QuoteRepository
) {
    operator fun invoke(): Flow<Resource<List<Quote>>> {
        return repository.getQuotes()
    }
}