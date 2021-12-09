package com.berker.wisdomoflife.ui.quote.list.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.recyclerview.widget.RecyclerView
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.databinding.RvItemQuiteBinding
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.ui.quote.list.extension.setFont
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
                ivRootImageView.setImage(quote.backgroundImageUrl)
            }
            tvContent.text = quote.content
            tvAuthor.text = quote.author

            setFontToTextViews(quote.textFont)

            tvContent.textSize = quote.textSize.value
            tvContent.textAlignment = quote.textHorizontalOrientation.value
            tvContent.setTextColor(quote.textColor.value)

            wvRootWeather.setWeatherData(PrecipType.SNOW)
            wvRootWeather.fadeOutPercent = 100f
            wvRootWeather.angle = Random.nextInt(0, 80)
            wvRootWeather.speed = Random.nextInt(100, 500)
            val randomLeafSize = Random.nextInt(20, 60)
            wvRootWeather.setCustomBitmap(
                Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(
                        itemView.resources,
                        R.drawable.leaf
                    ),
                    randomLeafSize, randomLeafSize, false
                )

            )

            clItemRoot.setBackgroundColor(
                itemBinding.root.resources.getColor(
                    quote.backgroundColor.value,
                    null
                )
            )
        }
    }

    private fun setFontToTextViews(fontId: Int) {
        itemBinding.apply {
            tvAuthor.setFont(fontId)
            tvContent.setFont(fontId)
        }
    }
}