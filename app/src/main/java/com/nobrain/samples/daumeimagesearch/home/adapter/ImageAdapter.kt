package com.nobrain.samples.daumeimagesearch.home.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.nobrain.samples.daumeimagesearch.R
import com.nobrain.samples.daumeimagesearch.domain.search.ImageItem
import com.nobrain.samples.daumeimagesearch.views.OnRecyclerItemClickListener
import java.util.*

class ImageAdapter(private val context: Context) : RecyclerView.Adapter<ImageAdapter.SearchViewHolder>(), ImageAdapterDataModel, ImageAdapterDataView {
    private val items: MutableList<ImageItem>
    private var onRecyclerItemClickListener: OnRecyclerItemClickListener? = null

    init {
        this.items = ArrayList<ImageItem>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageAdapter.SearchViewHolder {
        return LayoutInflater.from(context).run {
            SearchViewHolder(inflate(R.layout.item_search_result, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ImageAdapter.SearchViewHolder, position: Int) {
        getItem(position).apply {
            holder.tvTitle.text = Html.fromHtml(title)
            Glide.with(context).load(thumbnail).into(holder.ivThumbnail)
        }
    }

    override fun getItemCount(): Int = size

    override fun add(imageItem: ImageItem) {
        items.add(imageItem)
    }

    override val size: Int
        get() = items.size

    override fun getItem(position: Int): ImageItem = items[position]

    override fun clear() {
        items.clear()
    }

    override fun refresh() {
        notifyDataSetChanged()
    }

    override fun setOnRecyclerItemClickListener(onRecyclerItemClickListener: OnRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.tv_item_search_result_title)
        lateinit var tvTitle: TextView

        @BindView(R.id.iv_item_search_result_thumb)
        lateinit var ivThumbnail: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}
