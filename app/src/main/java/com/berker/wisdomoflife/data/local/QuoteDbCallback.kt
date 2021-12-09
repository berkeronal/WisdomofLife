package com.berker.wisdomoflife.data.local

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.data.local.entity.QuoteEntity
import com.berker.wisdomoflife.domain.model.QuoteHorizontalOrientation
import com.berker.wisdomoflife.domain.model.QuoteTextColor
import com.berker.wisdomoflife.domain.model.QuoteTextSize
import com.berker.wisdomoflife.domain.model.QuoteWeatherType
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
                    "Heroes who shed their blood and lost their lives! You are now lying in the soil of a friendly country. Therefore rest in peace. There is no difference between the Johnnies and Mehmets to us where they lie side by side here in this country of ours. You, the mothers, who sent their sons from far away countries wipe away your tears; your sons are now lying in our bosom and are in peace. After having lost their lives on this land they have become our sons as well.",
                    "Mustafa Kemal Atatürk",
                    R.font.handlee,
                    QuoteTextSize.SMALL.value,
                    QuoteTextColor.GREEN.value,
                    QuoteWeatherType.RAIN.value,
                    "",
                    R.color.quote_blue,
                    1,
                    QuoteHorizontalOrientation.MIDDLE.value,
                ),
                QuoteEntity(
                    "The greatest glory in living lies not in never falling, but in rising every time we fall.",
                    "Nelson Mandela",
                    R.font.eater,
                    QuoteTextSize.SMALL.value,
                    QuoteTextColor.AQUA.value,
                    QuoteWeatherType.SNOW.value,
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
                    QuoteWeatherType.AUTUMN.value,
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
                    QuoteWeatherType.NONE.value,
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
                    QuoteWeatherType.SNOW.value,
                    "",
                    R.color.quote_green,
                    1,
                    QuoteHorizontalOrientation.MIDDLE.value,
                ),
                QuoteEntity(
                    "Spread love everywhere you go. Let no one ever come to you without leaving happier. ",
                    "Mother Teresa",
                    R.font.aguafina_script,
                    QuoteTextSize.MEDIUM.value,
                    QuoteTextColor.GREEN.value,
                    QuoteWeatherType.RAIN.value,
                    "",
                    R.color.quote_orange,
                    1,
                    QuoteHorizontalOrientation.MIDDLE.value,
                ),
                QuoteEntity(
                    "Tell me and I forget. Teach me and I remember. Involve me and I learn.",
                    "Benjamin Franklin",
                    R.font.monoton,
                    QuoteTextSize.BIG.value,
                    QuoteTextColor.GREEN.value,
                    QuoteWeatherType.SNOW.value,
                    "",
                    R.color.quote_red,
                    1,
                    QuoteHorizontalOrientation.MIDDLE.value,
                ),
                QuoteEntity(
                    "It is during our darkest moments that we must focus to see the light.",
                    "Aristotle",
                    R.font.monoton,
                    QuoteTextSize.BIG.value,
                    QuoteTextColor.GREEN.value,
                    QuoteWeatherType.RAIN.value,
                    "",
                    R.color.quote_blue,
                    1,
                    QuoteHorizontalOrientation.MIDDLE.value,
                ),
                QuoteEntity(
                    "It is during our darkest moments that we must focus to see the light.",
                    "Aristotle",
                    R.font.monoton,
                    QuoteTextSize.BIG.value,
                    QuoteTextColor.GREEN.value,
                    QuoteWeatherType.SNOW.value,
                    "",
                    R.color.quote_blue,
                    1,
                    QuoteHorizontalOrientation.MIDDLE.value,
                ),
                QuoteEntity(
                    "It is during our darkest moments that we must focus to see the light.",
                    "Aristotle",
                    R.font.monoton,
                    QuoteTextSize.BIG.value,
                    QuoteTextColor.GREEN.value,
                    QuoteWeatherType.AUTUMN.value,
                    "",
                    R.color.quote_blue,
                    1,
                    QuoteHorizontalOrientation.MIDDLE.value,
                ),
            )
        )
    }
}