package com.nobrain.samples.daumeimagesearch.network.dagger

import com.nobrain.samples.daumeimagesearch.network.SearchApi
import com.nobrain.samples.daumeimagesearch.network.retrofit.RetrofitCreator

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit {
        return RetrofitCreator.createRetrofit()
    }

    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi {
        return SearchApi(retrofit)
    }
}
