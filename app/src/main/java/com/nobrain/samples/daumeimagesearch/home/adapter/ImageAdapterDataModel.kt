package com.nobrain.samples.daumeimagesearch.home.adapter

import com.nobrain.samples.daumeimagesearch.domain.search.ImageItem

interface ImageAdapterDataModel {
    fun add(imageItem: ImageItem)
    val size: Int
    fun getItem(position: Int): ImageItem
    fun clear()
}
