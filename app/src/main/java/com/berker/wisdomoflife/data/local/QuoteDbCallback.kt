package com.berker.wisdomoflife.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.data.local.entity.QuoteEntity
import com.berker.wisdomoflife.domain.model.QuoteHorizontalOrientation
import com.berker.wisdomoflife.domain.model.QuoteTextColor
import com.berker.wisdomoflife.domain.model.QuoteTextSize
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
                    "The greatest glory in living lies not in never falling, but in rising every time we fall.",
                    "Nelson Mandela",
                    R.font.abril_fatface,
                    QuoteTextSize.SMALL.value,
                    QuoteTextColor.AQUA.value,
                    "",
                    R.color.quote_green,
                    1,
                    QuoteHorizontalOrientation.MIDDLE.value,
                ),
                QuoteEntity(
                    "Life is really simple, but we insist on making it complicated.",
                    "Confucius",
                    R.font.aclonica,
                    QuoteTextSize.MEDIUM.value,
                    QuoteTextColor.RED.value,
                    "",
                    R.color.quote_orange,
                    1,
                    QuoteHorizontalOrientation.LEFT.value,
                ),
                QuoteEntity(
                    "If you set your goals ridiculousl high and it's a failure, you will fail above everyone else's success.",
                    "Jameson Cameron",
                    R.font.amatic_sc,
                    QuoteTextSize.BIG.value,
                    QuoteTextColor.ORANGE.value,
                    "",
                    R.color.quote_mint,
                    1,
                    QuoteHorizontalOrientation.RIGHT.value,
                ),
                QuoteEntity(
                    "You know you are on the road to success if you would do your job and not be paid for it.",
                    "Oprah Winfrey",
                    R.font.droid_sans_mono,
                    QuoteTextSize.MEDIUM.value,
                    QuoteTextColor.GREEN.value,
                    "",
                    R.color.quote_green,
                    1,
                    QuoteHorizontalOrientation.MIDDLE.value,
                ),
            )
        )
    }
}