package com.berker.wisdomoflife.data.repository

import com.berker.wisdomoflife.common.Resource
import com.berker.wisdomoflife.data.local.QuoteDao
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.repository.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import java.io.IOException

class QuoteRepositoryImpl(
    private val dao: QuoteDao
) : QuoteRepository {
    override fun getQuotes(): Flow<Resource<List<Quote>>> = channelFlow {
        trySend(Resource.Loading())

        try {
            dao.getQuotes().collectLatest {
                val quotes = it.map { x -> x.toQuote() }
                trySend(Resource.Success(quotes))
            }
        } catch (e: IOException) {
            trySend(Resource.Error(e.localizedMessage ?: "Error"))
        }
    }

    override fun getQuote(quoteId: Int): Flow<Resource<Quote>> = flow {
        emit(Resource.Loading())

        try {
            val quote = dao.getQuote(quoteId).toQuote()
            emit(Resource.Success(data = quote))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage ?: "Error"))
        }
    }

    override suspend fun insertQuote(newQuote: Quote) {
        dao.insertQuote(quote = newQuote.toQuoteEntity())
    }
}