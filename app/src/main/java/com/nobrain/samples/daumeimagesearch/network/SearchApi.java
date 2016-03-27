package com.nobrain.samples.daumeimagesearch.network;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class SearchApi {

    private Retrofit retrofit;

    @Inject
    public SearchApi(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    interface Api {

    }
}
