package com.nobrain.samples.daumeimagesearch.home.dagger;

import com.nobrain.samples.daumeimagesearch.home.view.HomeActivity;

import dagger.Component;

@Component(modules = HomeModule.class)
public interface HomeComponent {
    void inject(HomeActivity homeActivity);
}
