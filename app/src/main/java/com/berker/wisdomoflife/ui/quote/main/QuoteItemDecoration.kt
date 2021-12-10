package com.berker.wisdomoflife.ui.quote.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class QuoteItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State,
    ) {
        with(outRect) {
            top =
                if (parent.getChildAdapterPosition(view) == 0) {
                    spaceHeight
                } else 0
            left = spaceHeight
            right = spaceHeight
            bottom = spaceHeight
        }
    }
}