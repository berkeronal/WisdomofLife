package com.berker.wisdomoflife.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.berker.wisdomoflife.common.Constants.DB_VERSION
import com.berker.wisdomoflife.common.DatabaseInitialDataProvider
import com.berker.wisdomoflife.data.local.entity.QuoteEntity
import java.util.concurrent.Executors

@Database(
    entities = [QuoteEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class QuoteDatabase : RoomDatabase() {

    abstract val quoteDao: QuoteDao

    companion object {
        const val DB_NAME = "QUOTES_DATABASE"

        private val lock = Any()

        @Volatile
        private var quoteDatabase: QuoteDatabase? = null
        fun provideDatabase(context: Context) = quoteDatabase ?: synchronized(lock) {
            quoteDatabase ?: buildDatabase(context).also {
                quoteDatabase = it
            }
        }

        private fun buildDatabase(context: Context): QuoteDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                QuoteDatabase::class.java,
                DB_NAME
            ).addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    Executors.newSingleThreadExecutor().execute {
                        provideDatabase(context).quoteDao.insertQuotes(
                            DatabaseInitialDataProvider.getInitialEntities()
                        )
                    }
                }
            }).build()
        }
    }
}