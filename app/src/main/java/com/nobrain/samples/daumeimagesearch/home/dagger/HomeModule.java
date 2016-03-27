package com.nobrain.samples.daumeimagesearch.home.dagger;

import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapter;
import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapterDataModel;
import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapterDataView;
import com.nobrain.samples.daumeimagesearch.home.presenter.HomePresenter;
import com.nobrain.samples.daumeimagesearch.home.presenter.HomePresenterImpl;
import com.nobrain.samples.daumeimagesearch.network.dagger.NetworkModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = NetworkModule.class)
public class HomeModule {
    private HomePresenter.View view;
    private ImageAdapter adapter;

    public HomeModule(HomePresenter.View view, ImageAdapter adapter) {
        this.view = view;
        this.adapter = adapter;
    }

    @Provides
    ImageAdapterDataModel provideImageAdapterDataModel() {
        return adapter;
    }

    @Provides
    ImageAdapterDataView provideImageAdapterDataView() {
        return adapter;
    }

    @Provides
    HomePresenter provideHomePresenter(HomePresenterImpl homePresenter) {
        return homePresenter;
    }

    @Provides
    HomePresenter.View provideView() {
        return view;
    }

}
