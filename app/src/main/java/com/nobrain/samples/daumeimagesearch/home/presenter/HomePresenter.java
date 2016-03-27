package com.nobrain.samples.daumeimagesearch.home.presenter;

public interface HomePresenter {

    void inputSearchText(String searchText);

    void unSubscribeSearch();

    void onItemClick(int position);

    interface View {

        void refresh();

        void onMoveLink(String link);
    }
}
