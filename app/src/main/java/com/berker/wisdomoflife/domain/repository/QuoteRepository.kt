package com.berker.wisdomoflife.domain.repository

import com.berker.wisdomoflife.common.Resource
import com.berker.wisdomoflife.domain.model.Quote
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

    fun getQuotes(): Flow<Resource<List<Quote>>>
    fun getQuote(quoteId: Int): Flow<Resource<Quote>>
    suspend fun insertQuote(newQuote: Quote)
}