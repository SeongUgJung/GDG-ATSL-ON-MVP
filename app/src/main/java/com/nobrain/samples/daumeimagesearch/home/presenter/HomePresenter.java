package com.nobrain.samples.daumeimagesearch.home.presenter;

public interface HomePresenter {

    void inputSearchText(String searchText);

    void unSubscribeSearch();

    interface View {

        void refresh();
    }
}
