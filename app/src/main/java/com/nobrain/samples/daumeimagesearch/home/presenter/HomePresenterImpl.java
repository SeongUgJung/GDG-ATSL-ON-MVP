package com.nobrain.samples.daumeimagesearch.home.presenter;

import com.nobrain.samples.daumeimagesearch.network.SearchApi;

import javax.inject.Inject;

public class HomePresenterImpl implements HomePresenter {

    private View view;
    private SearchApi searchApi;

    @Inject
    public HomePresenterImpl(View view, SearchApi searchApi) {
        this.view = view;
        this.searchApi = searchApi;
    }
}
