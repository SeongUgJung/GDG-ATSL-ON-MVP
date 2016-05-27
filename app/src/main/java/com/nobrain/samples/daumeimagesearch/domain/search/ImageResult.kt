package com.nobrain.samples.daumeimagesearch.domain.search

class ImageResult {
    var result: Int = 0
    var pageCount: Int = 0
    var title: String? = null
    var totalCount: Int = 0
    var description: String? = null
    var item: List<ImageItem>? = null
}
