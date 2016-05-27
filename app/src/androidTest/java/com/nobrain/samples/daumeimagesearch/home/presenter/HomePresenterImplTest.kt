package com.nobrain.samples.daumeimagesearch.home.presenter

import android.support.test.runner.AndroidJUnit4
import com.jayway.awaitility.Awaitility.await
import com.nobrain.samples.daumeimagesearch.domain.search.ImageItem
import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapterDataModel
import com.nobrain.samples.daumeimagesearch.network.SearchApi
import com.nobrain.samples.daumeimagesearch.network.retrofit.RetrofitCreator
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.any
import org.mockito.Matchers.eq
import org.mockito.Mockito.*
import rx.observers.TestSubscriber

@RunWith(AndroidJUnit4::class)
class HomePresenterImplTest {
    private var homePresenter: HomePresenterImpl? = null
    private var mockView: HomePresenter.View? = null
    private var mockAdapterDataModel: ImageAdapterDataModel? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        mockAdapterDataModel = mock(ImageAdapterDataModel::class.java)
        mockView = mock(HomePresenter.View::class.java)
        homePresenter = HomePresenterImpl(mockView!!, SearchApi(RetrofitCreator.createRetrofit()), mockAdapterDataModel!!)
    }

    @Test
    @Throws(Exception::class)
    fun testInputSearchText_EmptyText() {
        homePresenter!!.inputSearchText("")
        verify<HomePresenter.View>(mockView).refresh()
        verify<ImageAdapterDataModel>(mockAdapterDataModel).clear()
    }

    @Test
    @Throws(Exception::class)
    fun testInputSearchText() {
        val testSubscriber = TestSubscriber.create<String>()
        homePresenter!!.searchSubscription.unsubscribe()
        homePresenter!!.searchSubject.subscribe(testSubscriber)

        homePresenter!!.inputSearchText("coupang")
        Thread.sleep(210)

        testSubscriber.assertValue("coupang")
        verify<ImageAdapterDataModel>(mockAdapterDataModel).clear()

    }

    @Test
    @Throws(Exception::class)
    fun testUnSubscribeSearch() {
        val oldSubscription = homePresenter!!.searchSubscription
        homePresenter!!.unSubscribeSearch()
        assertThat(oldSubscription.isUnsubscribed, `is`(true))

    }

    @Test
    @Throws(Exception::class)
    fun testOnItemClick() {
        // Given
        val mockItem = mock(ImageItem::class.java)
        val link = "http://www.coupang.com"
        doReturn(link).`when`(mockItem).link
        doReturn(mockItem).`when`<ImageAdapterDataModel>(mockAdapterDataModel).getItem(eq(0))

        // When
        homePresenter!!.onItemClick(0)

        // Then
        verify<HomePresenter.View>(mockView).onMoveLink(eq(link))
    }

    @Test
    @Throws(Exception::class)
    fun testLoadSearchResult() {
        val finish = booleanArrayOf(false)
        doAnswer { invocationOnMock ->
            finish[0] = true
            invocationOnMock
        }.`when`<HomePresenter.View>(mockView).refresh()

        homePresenter!!.loadSearchResult("coupang")

        await().until<Any> { finish[0] }

        verify<ImageAdapterDataModel>(mockAdapterDataModel, atLeast(1)).add(any<ImageItem>())
        verify<HomePresenter.View>(mockView, times(1)).refresh()
    }
}