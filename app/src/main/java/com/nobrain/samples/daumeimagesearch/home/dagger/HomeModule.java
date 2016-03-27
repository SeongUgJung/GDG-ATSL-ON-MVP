package com.nobrain.samples.daumeimagesearch.home.dagger;

import com.nobrain.samples.daumeimagesearch.home.presenter.HomePresenter;
import com.nobrain.samples.daumeimagesearch.home.presenter.HomePresenterImpl;
import com.nobrain.samples.daumeimagesearch.network.dagger.NetworkModule;

import dagger.Module;
import dagger.Provides;

@Module(includes = NetworkModule.class)
public class HomeModule {
    private HomePresenter.View view;

    public HomeModule(HomePresenter.View view) {
        this.view = view;
    }

    @Provides
    public HomePresenter provideHomePresenter(HomePresenterImpl homePresenter) {
        return homePresenter;
    }

    @Provides
    HomePresenter.View provideView() {
        return view;
    }

}
