package com.nobrain.samples.daumeimagesearch.home.dagger

import com.nobrain.samples.daumeimagesearch.home.view.HomeActivity

import dagger.Component

@Component(modules = arrayOf(HomeModule::class))
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
}
