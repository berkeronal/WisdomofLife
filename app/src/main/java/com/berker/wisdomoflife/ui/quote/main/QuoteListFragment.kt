package com.berker.wisdomoflife.ui.quote.main

import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.databinding.FragmentQuoteListBinding
import com.berker.wisdomoflife.domain.model.Quote
import com.berker.wisdomoflife.ui.base.BaseFragment
import com.berker.wisdomoflife.ui.quote.main.adapter.QuoteListAdapter
import com.berker.wisdomoflife.ui.quote.main.util.QuoteListUiEvent
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
        initSwipe()

        viewModel.getQuotes()
        binding.quoteListViewModel = viewModel
        binding.executePendingBindings()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.quoteListState.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest { quoteListState ->
                if (quoteListState.quoteList.isNullOrEmpty()) {
                    return@collectLatest
                }
                setRvData(quoteListState.quoteList)

                binding.quoteListState = quoteListState
                binding.executePendingBindings()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.quoteUiEventFlow.flowWithLifecycle(
                viewLifecycleOwner.lifecycle,
                Lifecycle.State.STARTED
            ).collectLatest { quoteListUiEvent ->
                when (quoteListUiEvent) {
                    QuoteListUiEvent.AddQuote -> {
                        findNavController().navigate(
                            QuoteListFragmentDirections.actionQuoteListFragmentToDetailFragment(-1)
                        )
                    }
                    is QuoteListUiEvent.ShowErrorDialog ->{

                    }
                }
            }
        }
    }

    private fun initSwipe() {
        val simpleCallback = (object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                viewModel.onItemDelete((binding.rvQuotesList.adapter as QuoteListAdapter).getItem(
                    position))
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean,
            ) {
                val paint = Paint()
                viewHolder.itemView.let {
                    if (dX > 0) {
                        val icon = BitmapFactory.decodeResource(
                            context?.resources, R.drawable.trash)
                        context?.let { context ->
                            paint.color = ContextCompat.getColor(context, R.color.quote_red_dark)
                        }
                        c.drawRect(
                            it.left.toFloat(),
                            it.top.toFloat(),
                            dX,
                            it.bottom.toFloat(),
                            paint
                        )
                        c.drawBitmap(
                            icon,
                            it.left.toFloat() + resources.getDimension(R.dimen.icon_trash_padding)
                                .toInt(),
                            it.top.toFloat() + (it.bottom - it.top - icon.height) / 2,
                            paint
                        )
                    }
                    /*
                     private var isDeleting = false
                     val swipeRatio: Float = 1f - Math.abs(dX) / viewHolder.itemView.width
                     if (swipeRatio <= 0.85 && !isDeleting) {
                         isDeleting = true
                         onSwiped(viewHolder, ItemTouchHelper.RIGHT)
                         Toast.makeText(requireContext(), "deleted", Toast.LENGTH_SHORT).show()
                     }*/
                }
                super.onChildDraw(c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive)
            }
        })
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvQuotesList)
    }
    private fun setRvData(quoteList: List<Quote>) {
        quoteListAdapter.updateData(quoteList)
    }

    private fun initRecyclerView() {
        binding.adapter = quoteListAdapter
        binding.rvQuotesList.apply {
            addItemDecoration(
                QuoteItemDecoration(
                    resources.getDimension(R.dimen.rv_padding).toInt()
                )
            )
        }
    }
}