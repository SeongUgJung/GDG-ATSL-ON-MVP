package com.nobrain.samples.daumeimagesearch.network

import android.support.test.runner.AndroidJUnit4
import com.nobrain.samples.daumeimagesearch.domain.search.SearchChannel
import com.nobrain.samples.daumeimagesearch.network.retrofit.RetrofitCreator
import org.hamcrest.Matchers.greaterThan
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import rx.observers.TestSubscriber

@RunWith(AndroidJUnit4::class)
class SearchApiTest {

    private var searchApi: SearchApi? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        searchApi = SearchApi(RetrofitCreator.createRetrofit())
    }

    @Test
    @Throws(Exception::class)
    fun testSearchText() {
        run {
            val subscriber = TestSubscriber.create<SearchChannel>()
            searchApi!!.searchText("", 1).subscribe(subscriber)

            val onErrorEvents = subscriber.onErrorEvents
            assertThat(onErrorEvents.size, `is`(greaterThan(0)))
        }

        run {
            val subscriber = TestSubscriber.create<SearchChannel>()
            searchApi!!.searchText("kakao", 1).subscribe(subscriber)

            subscriber.assertCompleted()
            subscriber.assertNoErrors()
            subscriber.assertValueCount(1)
        }
    }
}