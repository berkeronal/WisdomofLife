package com.berker.wisdomoflife.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.data.local.entity.QuoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Provider

class QuoteDbCallback(
    private val provider: Provider<QuoteDao>
) : RoomDatabase.Callback() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        applicationScope.launch(Dispatchers.IO) {
            createInitialRecords()
        }
    }
    //TODO(find 10 quotes)
    private fun createInitialRecords() {
        provider.get().insertQuotes(
            listOf(
                QuoteEntity(
                    "berrker",
                    "berrrkerr",
                    "",
                    R.font.aclonica,
                    11,
                    "",
                    R.color.note_aqua,
                    1,
                    1,
                ),
                QuoteEntity(
                    "berrker",
                    "berrrkerr",
                    "",
                    R.font.aclonica,
                    11,
                    "",
                    R.color.note_aqua,
                    1,
                    1,
                ),
                QuoteEntity(
                    "berrker",
                    "berrrkerr",
                    "",
                    R.font.aclonica,
                    11,
                    "",
                    R.color.note_aqua,
                    1,
                    1,
                )
            )
        )
    }
}