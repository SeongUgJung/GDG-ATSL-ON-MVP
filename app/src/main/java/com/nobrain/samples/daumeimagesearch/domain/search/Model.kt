package com.nobrain.samples.daumeimagesearch.domain.search

data class ImageItem(var title: String?,
                     var link: String?,
                     var image: String?,
                     var thumbnail: String?
)

data class ImageResult(
        var result: Int = 0,
        var pageCount: Int = 0,
        var title: String? = null,
        var totalCount: Int = 0,
        var description: String? = null,
        var item: List<ImageItem>? = null
)

data class SearchChannel(
        var channel: ImageResult? = null
)
