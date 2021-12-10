package com.berker.wisdomoflife.di

import android.app.Application
import com.berker.wisdomoflife.data.local.QuoteDao
import com.berker.wisdomoflife.data.local.QuoteDatabase
import com.berker.wisdomoflife.data.repository.QuoteRepositoryImpl
import com.berker.wisdomoflife.domain.repository.QuoteRepository
import com.berker.wisdomoflife.domain.usecase.AddQuoteUseCase
import com.berker.wisdomoflife.domain.usecase.GetQuotesUseCase
import com.berker.wisdomoflife.domain.usecase.QuoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideQuotesDatabase(
        app: Application,
    ): QuoteDatabase {
        return QuoteDatabase.provideDatabase(app)
    }

    @Provides
    @Singleton
    fun provideQuoteDatabaseDao(db: QuoteDatabase): QuoteDao {
        return db.quoteDao
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(db: QuoteDatabase): QuoteRepository {
        return QuoteRepositoryImpl(db.quoteDao)
    }

    @Provides
    @Singleton
    fun provideQuoteUseCases(repository: QuoteRepository): QuoteUseCases {
        return QuoteUseCases(
            getQuotesUseCase = GetQuotesUseCase(repository),
            addQuote = AddQuoteUseCase(repository)
        )
    }
}