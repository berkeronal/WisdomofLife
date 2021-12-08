package com.berker.wisdomoflife.ui.quote.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.berker.wisdomoflife.R
import com.berker.wisdomoflife.databinding.FragmentQuoteListBinding
import com.berker.wisdomoflife.ui.base.BaseFragment


class QuoteListFragment : BaseFragment<FragmentQuoteListBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quote_list, container, false)
    }

    override fun layoutId(): Int = R.layout.fragment_quote_list

    override fun initUi() {
    }
}