package com.berker.wisdomoflife.ui.quote.detail

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.databinding.FragmentQuoteDetailBinding
import com.berker.wisdomoflife.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
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
        binding.quoteDetailViewModel = quoteDetailViewModel
        binding.fontClick = QuoteDetailEvent.ChangeTextFont


        viewLifecycleOwner.lifecycleScope.launch {
            quoteDetailViewModel.quoteDetailState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest { quoteDetailState ->
                binding.quoteDetailState = quoteDetailState
                binding.executePendingBindings()
                Toast.makeText(context, "veri ${quoteDetailState.textFont}", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun restoreQuote() {
        if (quoteId != -1)
            return
        quoteDetailViewModel.restoreQuote(quoteId)
    }
}