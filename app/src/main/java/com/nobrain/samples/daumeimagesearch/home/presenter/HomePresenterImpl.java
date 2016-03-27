package com.nobrain.samples.daumeimagesearch.home.presenter;

import android.text.TextUtils;

import com.nobrain.samples.daumeimagesearch.domain.search.SearchChannel;
import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapterDataModel;
import com.nobrain.samples.daumeimagesearch.network.SearchApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class HomePresenterImpl implements HomePresenter {

    // DI Object
    private View view;
    private SearchApi searchApi;
    private ImageAdapterDataModel imageAdapterDataModel;

    private PublishSubject<String> searchSubject;
    private Subscription searchSubscription;
    private int pageCount = 1;

    @Inject
    public HomePresenterImpl(View view, SearchApi searchApi, ImageAdapterDataModel imageAdapterDataModel) {
        this.view = view;
        this.searchApi = searchApi;
        this.imageAdapterDataModel = imageAdapterDataModel;

        searchSubject = PublishSubject.create();
        initSubscription();
    }

    private void initSubscription() {
        searchSubscription = searchSubject
                .onBackpressureBuffer()
                .throttleWithTimeout(200, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(text -> {
                    searchApi.searchText(text, pageCount)
                            .filter(channel -> channel != null && channel.getChannel() != null)
                            .map(SearchChannel::getChannel)
                            .filter(result -> result != null && result.getResult() > 0)
                            .flatMap(imageResult -> Observable.from(imageResult.getItem()))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(imageAdapterDataModel::add,
                                    Throwable::printStackTrace,
                                    view::refresh);

                }, Throwable::printStackTrace);
    }

    @Override
    public void inputSearchText(String searchText) {

        if (!searchSubscription.isUnsubscribed()) {
            searchSubscription.unsubscribe();
        }
        imageAdapterDataModel.clear();
        initSubscription();
        if (!TextUtils.isEmpty(searchText)) {
            searchSubject.onNext(searchText);
        } else {
            view.refresh();
        }
    }

    @Override
    public void unSubscribeSearch() {
        searchSubscription.unsubscribe();
    }
}
