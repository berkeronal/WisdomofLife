package com.berker.wisdomoflife.ui.quote.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.recyclerview.widget.RecyclerView
import com.berker.wisdomoflife.databinding.RvItemQuiteBinding
import com.berker.wisdomoflife.domain.model.Quote

class QuoteListAdapter(
    var itemClickListener: ((Quote) -> Unit)? = null
) : RecyclerView.Adapter<QuoteListViewHolder>() {

    private var itemList: List<Quote> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteListViewHolder {
        val itemBinding =
            RvItemQuiteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteListViewHolder(itemBinding, itemClickListener)
    }

    override fun onBindViewHolder(holder: QuoteListViewHolder, position: Int) {
        setFadeAnimation(holder.itemView)
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun updateData(newItemList: List<Quote>) {
        itemList = newItemList
        notifyDataSetChanged()
    }

    private fun setFadeAnimation(view: View) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 200
        view.startAnimation(anim)
    }
}