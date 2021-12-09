package com.berker.wisdomoflife.data.repository

import com.berker.wisdomoflife.common.Resource
import com.berker.wisdomoflife.data.local.QuoteDao
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class QuoteRepositoryImpl(
    private val dao: QuoteDao
) : QuoteRepository {
    override fun getQuotes(): Flow<Resource<List<Quote>>> = flow {
        emit(Resource.Loading())

        try {
            val quotes = dao.getQuotes().map { it.toQuote() }
            emit(Resource.Success(quotes))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }

    override suspend fun insertQuote(newQuote: Quote) {
        dao.insertQuote(quote = newQuote.toQuoteEntity())
    }
}