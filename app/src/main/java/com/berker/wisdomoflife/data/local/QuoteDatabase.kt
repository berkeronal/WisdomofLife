package com.berker.wisdomoflife.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.berker.wisdomoflife.common.Constants.DB_VERSION
import com.berker.wisdomoflife.data.local.entity.QuoteEntity

@Database(
    entities = [QuoteEntity::class],
    version = DB_VERSION,
)
abstract class QuoteDatabase : RoomDatabase() {

    abstract val dao: QuoteDao

    companion object {
        const val DB_NAME = "QUOTES_DATABASE"
    }
}