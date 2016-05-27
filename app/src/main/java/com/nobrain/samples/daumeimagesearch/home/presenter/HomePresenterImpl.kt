package com.nobrain.samples.daumeimagesearch.home.presenter

import android.text.TextUtils
import com.nobrain.samples.daumeimagesearch.domain.search.ImageItem
import com.nobrain.samples.daumeimagesearch.domain.search.ImageResult
import com.nobrain.samples.daumeimagesearch.domain.search.SearchChannel
import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapterDataModel
import com.nobrain.samples.daumeimagesearch.network.SearchApi
import rx.Observable
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Func1
import rx.schedulers.Schedulers
import rx.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomePresenterImpl
@Inject
constructor(// DI Object
        private val view: HomePresenter.View, private val searchApi: SearchApi, private val imageAdapterDataModel: ImageAdapterDataModel) : HomePresenter {

    var searchSubject: PublishSubject<String>
    lateinit var searchSubscription: Subscription
    private val pageCount = 1

    init {

        searchSubject = PublishSubject.create<String>()
        initSubscription()
    }

    private fun initSubscription() {
        searchSubscription = searchSubject.onBackpressureBuffer()
                .throttleWithTimeout(200, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe({ text -> loadSearchResult(text) },
                        { it.printStackTrace() })
    }

    fun loadSearchResult(text: String) {
        searchApi.searchText(text, pageCount)
                .filter { channel -> channel != null && channel.channel != null }
                .map<ImageResult>(Func1<SearchChannel, ImageResult> { it.channel })
                .filter {
                    it?.totalCount?.let {
                        it > 0
                    }
                }
                .flatMap({ imageResult -> Observable.from<ImageItem>(imageResult.item) })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ imageAdapterDataModel.add(it) },
                        { it.printStackTrace() },
                        { view.refresh() })
    }

    override fun inputSearchText(searchText: String) {

        if (!searchSubscription.isUnsubscribed) {
            searchSubscription.unsubscribe()
        }
        imageAdapterDataModel.clear()
        initSubscription()
        if (!TextUtils.isEmpty(searchText)) {
            searchSubject.onNext(searchText)
        } else {
            view.refresh()
        }
    }

    override fun unSubscribeSearch() {
        searchSubscription.unsubscribe()
    }

    override fun onItemClick(position: Int) {
        val item = imageAdapterDataModel.getItem(position)
        item.link?.let {
            view.onMoveLink(it)
        }

    }
}
