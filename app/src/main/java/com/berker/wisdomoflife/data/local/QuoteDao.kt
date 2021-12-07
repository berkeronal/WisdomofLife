package com.berker.wisdomoflife.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.berker.wisdomoflife.data.local.entity.QuoteEntity

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: QuoteEntity)

    @Query("SELECT * FROM quoteentity")
    suspend fun getQuotes(): List<QuoteEntity>

    @Query("SELECT * FROM quoteentity WHERE id = :quoteId")
    suspend fun getQuote(quoteId: Int): QuoteEntity

    @Query("DELETE FROM quoteentity WHERE id= :quoteId")
    suspend fun deleteQuote(quoteId: Int)
}