package com.berker.wisdomoflife.ui.quote.list.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.databinding.RvItemQuiteBinding
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.model.QuoteTextColor
import com.berker.wisdomoflife.domain.model.QuoteWeatherType
import com.berker.wisdomoflife.domain.model.QuoteWeatherType.*
import com.berker.wisdomoflife.ui.quote.list.extension.setFontAndColor
import com.berker.wisdomoflife.ui.quote.list.extension.setImage
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
        itemBinding.apply {
            if (quote.backgroundImageUrl.isNotEmpty()) {
                ivRootImageView.visibility = View.VISIBLE
                ivRootImageView.alpha = quote.backgroundImageOpacity.value
                ivRootImageView.setImage(quote.backgroundImageUrl)

            } else {
                ivRootImageView.visibility = View.GONE
                ivRootImageView.alpha = quote.backgroundImageOpacity.value

            }
            tvContent.text = quote.content
            tvAuthor.text = quote.author

            setFontAndColorToTextViews(quote.textFont, quote.textColor)
            initWeather(quote.weatherType)

            tvContent.textSize = quote.textSize.value
            tvContent.textAlignment = quote.textHorizontalOrientation.value

            cvRootCard.setBackgroundColor(
                itemBinding.root.resources.getColor(
                    quote.backgroundColor.value,
                    null
                )
            )

        }
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

    private fun setFontAndColorToTextViews(fontId: Int, quoteTextColor: QuoteTextColor) {
        itemBinding.apply {
            tvAuthor.setFontAndColor(fontId, quoteTextColor.value)
            tvContent.setFontAndColor(fontId, quoteTextColor.value)
        }
    }
}