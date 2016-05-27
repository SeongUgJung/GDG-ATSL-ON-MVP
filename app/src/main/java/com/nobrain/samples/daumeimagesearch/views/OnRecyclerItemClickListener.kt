package com.nobrain.samples.daumeimagesearch.views

import android.support.v7.widget.RecyclerView

interface OnRecyclerItemClickListener {
    fun onItemClick(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, position: Int)
}
