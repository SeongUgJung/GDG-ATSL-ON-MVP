package com.nobrain.samples.daumeimagesearch.home.dagger

import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapter
import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapterDataModel
import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapterDataView
import com.nobrain.samples.daumeimagesearch.home.presenter.HomePresenter
import com.nobrain.samples.daumeimagesearch.home.presenter.HomePresenterImpl
import com.nobrain.samples.daumeimagesearch.network.dagger.NetworkModule

import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(NetworkModule::class))
class HomeModule(private val view: HomePresenter.View, private val adapter: ImageAdapter) {

    @Provides
    internal fun provideImageAdapterDataModel(): ImageAdapterDataModel = adapter

    @Provides
    internal fun provideImageAdapterDataView(): ImageAdapterDataView = adapter

    @Provides
    internal fun provideHomePresenter(homePresenter: HomePresenterImpl): HomePresenter = homePresenter

    @Provides
    internal fun provideView(): HomePresenter.View = view

}
