package com.nobrain.samples.daumeimagesearch.home.adapter;

import com.nobrain.samples.daumeimagesearch.domain.search.ImageItem;

public interface ImageAdapterDataModel {
    void add(ImageItem imageItem);
    int getSize();
    ImageItem getItem(int position);
    void clear();
}
