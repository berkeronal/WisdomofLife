package com.berker.wisdomoflife.ui.quote.list.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.berker.wisdomoflife.domain.model.Quote

class QuoteListViewHolder(
    private val itemBinding: ViewDataBinding,
    private val clickedItem: ((Quote) -> Unit)?
) : RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(quote: Quote) {

    }
}