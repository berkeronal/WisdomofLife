package com.berker.wisdomoflife.ui.quote.detail

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.R.drawable
import com.berker.wisdomoflife.common.setQuoteImage
import com.berker.wisdomoflife.databinding.FragmentQuoteDetailBinding
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.domain.model.QuoteHorizontalOrientation.*
import com.berker.wisdomoflife.domain.model.QuoteWeatherType
import com.berker.wisdomoflife.ui.base.BaseFragment
import com.berker.wisdomoflife.ui.quote.detail.util.QuoteDetailUiEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteDetailFragment : BaseFragment<FragmentQuoteDetailBinding>() {

    private var quoteId = -1
    private val quoteDetailViewModel: QuoteDetailViewModel by viewModels()
    private val args: QuoteDetailFragmentArgs by navArgs()

    override fun layoutId(): Int = R.layout.fragment_quote_detail

    override fun initOnCreateView(savedInstanceState: Bundle?) {
        quoteId = args.quoteDbId
    }

    override fun initUi() {
        restoreQuote()
        binding.quoteDetailViewModel = quoteDetailViewModel

        initTextChangeListeners()
        viewLifecycleOwner.lifecycleScope.launch {
            quoteDetailViewModel.quoteDetailState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest { quoteDetailState ->
                binding.quoteDetailState = quoteDetailState
                binding.executePendingBindings()
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            quoteDetailViewModel.quoteDetailItemViewState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest { quoteDetailItemViewState ->
                binding.quoteDetailItemViewState = quoteDetailItemViewState

                binding.ivRootImageView.setQuoteImage(quoteDetailItemViewState.quote.backgroundImageUrl)
                initWeather(quoteDetailItemViewState.quote.weatherType)

                handleEditTextOrientation(quoteDetailItemViewState.quote)


                binding.executePendingBindings()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            quoteDetailViewModel.quoteDetailUiEventFlow.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest { quoteDetailuiEvent ->
                when (quoteDetailuiEvent) {
                    QuoteDetailUiEvent.SaveQuote -> {
                        findNavController().navigateUp()
                    }
                    is QuoteDetailUiEvent.ShowErrorDialog -> {

                    }
                }
            }
        }
    }

    private fun handleEditTextOrientation(quote: Quote) {
        when (quote.textHorizontalOrientation) {
            LEFT -> {
                binding.etvContent.gravity = 0x03
            }
            MIDDLE -> {
                binding.etvContent.gravity = 0x11
            }
            RIGHT -> {
                binding.etvContent.gravity = 0x05
            }
        }
    }

    private fun initTextChangeListeners() {
        binding.etvContent.addTextChangedListener(
            afterTextChanged = { newText ->
                quoteDetailViewModel.onWroteContent(newText.toString())
            }
        )
        binding.etvAuthor.addTextChangedListener(
            afterTextChanged = { newText ->
                quoteDetailViewModel.onWroteAuthor(newText.toString())
            }
        )

        binding.etvBgUrl.addTextChangedListener(
            afterTextChanged = { newText ->
                quoteDetailViewModel.onWroteBackgroundUrl(newText.toString())
            }
        )
    }

    private fun initWeather(quoteWeatherType: QuoteWeatherType) {
        with(binding.wvRootWeather) {
            when (quoteWeatherType) {
                QuoteWeatherType.SNOW -> {
                    setWeatherData(com.github.matteobattilana.weather.PrecipType.SNOW)
                    fadeOutPercent = 100f
                    angle = kotlin.random.Random.nextInt(0, 80)
                    speed = kotlin.random.Random.nextInt(100, 500)
                }
                QuoteWeatherType.RAIN -> {
                    setWeatherData(com.github.matteobattilana.weather.PrecipType.RAIN)
                    fadeOutPercent = 100f
                    angle = kotlin.random.Random.nextInt(0, 80)
                    speed = kotlin.random.Random.nextInt(100, 500)
                }
                QuoteWeatherType.HEAVY_RAIN -> {
                    setWeatherData(com.github.matteobattilana.weather.PrecipType.RAIN)
                    emissionRate = 250f
                    fadeOutPercent = 100f
                    angle = kotlin.random.Random.nextInt(0, 80)
                    speed = kotlin.random.Random.nextInt(300, 500)
                }
                QuoteWeatherType.AUTUMN -> {
                    setWeatherData(com.github.matteobattilana.weather.PrecipType.RAIN)
                    emissionRate = 10f
                    fadeOutPercent = 100f
                    angle = kotlin.random.Random.nextInt(0, 80)
                    speed = kotlin.random.Random.nextInt(100, 500)
                    val randomLeafSize = kotlin.random.Random.nextInt(30, 60)
                    setCustomBitmap(
                        android.graphics.Bitmap.createScaledBitmap(
                            android.graphics.BitmapFactory.decodeResource(
                                resources,
                                drawable.leaf
                            ),
                            randomLeafSize, randomLeafSize, false
                        )
                    )
                }
                QuoteWeatherType.NONE -> {
                    setWeatherData(com.github.matteobattilana.weather.PrecipType.CLEAR)
                }
            }
        }
    }

    private fun restoreQuote() {
        if (quoteId == -1)
            return
        quoteDetailViewModel.restoreQuote(quoteId)
    }
}