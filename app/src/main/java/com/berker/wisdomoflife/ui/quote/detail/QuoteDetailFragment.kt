package com.berker.wisdomoflife.ui.quote.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.databinding.FragmentQuoteDetailBinding
import com.berker.wisdomoflife.ui.base.BaseFragment

class QuoteDetailFragment : BaseFragment<FragmentQuoteDetailBinding>() {

    private var quoteId = -1
    private val quoteDetailViewModel: QuoteDetailViewModel by viewModels()

    override fun layoutId(): Int = R.layout.fragment_quote_detail


    override fun initOnCreateView(savedInstanceState: Bundle?) {
        super.initOnCreateView(savedInstanceState)
        savedInstanceState?.let { bundle ->
            quoteId = QuoteDetailFragmentArgs.fromBundle(bundle).quoteDbId
        }
    }

    override fun initUi() {
        restoreQuote()
    }

    private fun restoreQuote() {
        if (quoteId == -1)
            return
        quoteDetailViewModel.restoreQuote(quoteId)
    }
}