package com.nobrain.samples.daumeimagesearch.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nobrain.samples.daumeimagesearch.R;
import com.nobrain.samples.daumeimagesearch.domain.search.ImageItem;
import com.nobrain.samples.daumeimagesearch.views.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.SearchViewHolder>
        implements ImageAdapterDataModel, ImageAdapterDataView {

    private Context context;
    private List<ImageItem> items;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public ImageAdapter(Context context) {
        this.context = context;
        this.items = new ArrayList<>();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_result, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        ImageItem item = getItem(position);
        holder.tvTitle.setText(Html.fromHtml(item.getTitle()));
        Glide.with(context)
                .load(item.getThumbnail())
                .into(holder.ivThumbnail);
    }

    @Override
    public int getItemCount() {
        return getSize();
    }

    @Override
    public void add(ImageItem imageItem) {
        items.add(imageItem);
    }

    @Override
    public int getSize() {
        return items.size();
    }

    @Override
    public ImageItem getItem(int position) {
        return items.get(position);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public void refresh() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_item_search_result_title)
        TextView tvTitle;

        @Bind(R.id.iv_item_search_result_thumb)
        ImageView ivThumbnail;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
