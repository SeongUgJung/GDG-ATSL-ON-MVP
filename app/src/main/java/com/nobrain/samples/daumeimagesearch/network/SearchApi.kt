package com.nobrain.samples.daumeimagesearch.network

import com.nobrain.samples.daumeimagesearch.BuildConfig
import com.nobrain.samples.daumeimagesearch.domain.search.SearchChannel
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable
import javax.inject.Inject

class SearchApi
@Inject
constructor(private val retrofit: Retrofit) {

    fun searchText(text: String, pageCount: Int): Observable<SearchChannel> {
        return retrofit.create(Api::class.java).getSearch(text, pageCount)
    }

    internal interface Api {
        @GET("image?output=json&apikey=" + BuildConfig.DAUM_API_KEY)
        fun getSearch(@Query("q") seachText: String, @Query("pageno") pageNo: Int): Observable<SearchChannel>
    }
}
