package com.berker.wisdomoflife.ui.quote.main.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.databinding.RvItemQuiteBinding
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.model.QuoteWeatherType
import com.berker.wisdomoflife.domain.model.QuoteWeatherType.*
import com.github.matteobattilana.weather.PrecipType
import kotlin.random.Random

class QuoteListViewHolder(
    private val itemBinding: RvItemQuiteBinding,
    private val clickedItem: ((Quote) -> Unit)?
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(quote: Quote) {
        itemView.setOnClickListener {
            clickedItem?.invoke(quote)
        }
        itemBinding.quoteListItemViewState = QuoteListItemViewState(quote)
        itemBinding.executePendingBindings()
        initWeather(quote.weatherType)
    }

    private fun initWeather(quoteWeatherType: QuoteWeatherType) {
        with(itemBinding.wvRootWeather) {
            when (quoteWeatherType) {
                SNOW -> {
                    setWeatherData(PrecipType.SNOW)
                    fadeOutPercent = 100f
                    angle = Random.nextInt(0, 80)
                    speed = Random.nextInt(100, 500)
                }
                RAIN -> {
                    setWeatherData(PrecipType.RAIN)
                    fadeOutPercent = 100f
                    angle = Random.nextInt(0, 80)
                    speed = Random.nextInt(100, 500)
                }
                HEAVY_RAIN -> {
                    setWeatherData(PrecipType.RAIN)
                    emissionRate = 250f
                    fadeOutPercent = 100f
                    angle = Random.nextInt(0, 80)
                    speed = Random.nextInt(300, 500)
                }
                AUTUMN -> {
                    setWeatherData(PrecipType.RAIN)
                    emissionRate = 10f
                    fadeOutPercent = 100f
                    angle = Random.nextInt(0, 80)
                    speed = Random.nextInt(100, 500)
                    val randomLeafSize = Random.nextInt(30, 60)
                    setCustomBitmap(
                        Bitmap.createScaledBitmap(
                            BitmapFactory.decodeResource(
                                itemView.resources,
                                R.drawable.leaf
                            ),
                            randomLeafSize, randomLeafSize, false
                        )
                    )
                }
                NONE -> {
                    setWeatherData(PrecipType.CLEAR)
                }
            }
        }
    }
}