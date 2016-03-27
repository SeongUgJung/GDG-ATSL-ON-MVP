package com.nobrain.samples.daumeimagesearch.home.presenter;

import android.support.test.runner.AndroidJUnit4;

import com.nobrain.samples.daumeimagesearch.domain.search.ImageItem;
import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapterDataModel;
import com.nobrain.samples.daumeimagesearch.network.SearchApi;
import com.nobrain.samples.daumeimagesearch.network.retrofit.RetrofitCreator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import rx.Subscription;
import rx.observers.TestSubscriber;

import static com.jayway.awaitility.Awaitility.await;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class HomePresenterImplTest {
    private HomePresenterImpl homePresenter;
    private HomePresenter.View mockView;
    private ImageAdapterDataModel mockAdapterDataModel;

    @Before
    public void setUp() throws Exception {
        mockAdapterDataModel = mock(ImageAdapterDataModel.class);
        mockView = mock(HomePresenter.View.class);
        homePresenter = new HomePresenterImpl(mockView, new SearchApi(RetrofitCreator.createRetrofit()), mockAdapterDataModel);
    }

    @Test
    public void testInputSearchText_EmptyText() throws Exception {
        homePresenter.inputSearchText("");
        verify(mockView).refresh();
        verify(mockAdapterDataModel).clear();
    }

    @Test
    public void testInputSearchText() throws Exception {
        TestSubscriber<String> testSubscriber = TestSubscriber.create();
        homePresenter.searchSubscription.unsubscribe();
        homePresenter.searchSubject.subscribe(testSubscriber);

        homePresenter.inputSearchText("coupang");
        Thread.sleep(210);

        testSubscriber.assertValue("coupang");
        verify(mockAdapterDataModel).clear();

    }

    @Test
    public void testUnSubscribeSearch() throws Exception {
        Subscription oldSubscription = homePresenter.searchSubscription;
        homePresenter.unSubscribeSearch();
        assertThat(oldSubscription.isUnsubscribed(), is(true));

    }

    @Test
    public void testOnItemClick() throws Exception {
        // Given
        ImageItem mockItem = mock(ImageItem.class);
        String link = "http://www.coupang.com";
        doReturn(link).when(mockItem).getLink();
        doReturn(mockItem).when(mockAdapterDataModel).getItem(eq(0));

        // When
        homePresenter.onItemClick(0);

        // Then
        verify(mockView).onMoveLink(eq(link));
    }

    @Test
    public void testLoadSearchResult() throws Exception {
        final boolean[] finish = {false};
        doAnswer(invocationOnMock -> {
            finish[0] = true;
            return invocationOnMock;
        }).when(mockView).refresh();

        homePresenter.loadSearchResult("coupang");

        await().until(() -> finish[0]);

        verify(mockAdapterDataModel, atLeast(1)).add(any());
        verify(mockView, times(1)).refresh();
    }
}