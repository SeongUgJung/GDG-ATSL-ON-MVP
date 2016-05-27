package com.nobrain.samples.daumeimagesearch.home.adapter

import com.nobrain.samples.daumeimagesearch.views.OnRecyclerItemClickListener

interface ImageAdapterDataView {
    fun refresh()

    fun setOnRecyclerItemClickListener(onRecyclerItemClickListener: OnRecyclerItemClickListener)
}
