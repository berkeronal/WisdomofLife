package com.berker.wisdomoflife.ui.quote.list

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.databinding.FragmentQuoteListBinding
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.ui.base.BaseFragment
import com.berker.wisdomoflife.ui.quote.list.adapter.QuoteListAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class QuoteListFragment : BaseFragment<FragmentQuoteListBinding>() {
    private val viewModel: QuoteListViewModel by viewModels()
    private val quoteListAdapter: QuoteListAdapter by lazy {
        QuoteListAdapter().apply {
            stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
            itemClickListener = {
                findNavController().navigate(
                    QuoteListFragmentDirections.actionQuoteListFragmentToDetailFragment(
                        it.db_id ?: -1
                    )
                )
            }
        }
    }

    override fun layoutId(): Int = R.layout.fragment_quote_list

    override fun initUi() {
        initRecyclerView()

        viewModel.getQuotes()
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.quoteListState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest { quoteListState ->
                if (quoteListState.quoteList.isNullOrEmpty()) {
                    return@collectLatest
                }
                setRvData(quoteListState.quoteList)
            }
        }
    }

    private fun setRvData(quoteList: List<Quote>) {
        quoteListAdapter.updateData(quoteList)
    }

    private fun initRecyclerView() {
        binding.adapter = quoteListAdapter
        binding.rvNotesList.apply {
            addItemDecoration(
                QuoteItemDecoration(
                    resources.getDimension(R.dimen.rv_padding).toInt()
                )
            )
        }
    }
}