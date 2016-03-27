package com.nobrain.samples.daumeimagesearch.network.dagger;

import com.nobrain.samples.daumeimagesearch.network.SearchApi;
import com.nobrain.samples.daumeimagesearch.network.retrofit.RetrofitCreator;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class NetworkModule {

    @Provides
    public Retrofit provideRetrofit() {
        return RetrofitCreator.createRetrofit();
    }

    @Provides
    public SearchApi provideSearchApi(Retrofit retrofit) {
        return new SearchApi(retrofit);
    }
}
