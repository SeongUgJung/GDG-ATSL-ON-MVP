package com.nobrain.samples.daumeimagesearch.network.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCreator {

    fun createRetrofit(): Retrofit {
        return Retrofit.Builder().apply {
            baseUrl("https://apis.daum.net/search/")
            addConverterFactory(GsonConverterFactory.create())
            addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        }.build()


    }
}
