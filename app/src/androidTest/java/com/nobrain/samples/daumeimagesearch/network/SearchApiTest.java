package com.nobrain.samples.daumeimagesearch.network;

import android.support.test.runner.AndroidJUnit4;

import com.nobrain.samples.daumeimagesearch.domain.search.SearchChannel;
import com.nobrain.samples.daumeimagesearch.network.retrofit.RetrofitCreator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import rx.observers.TestSubscriber;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class SearchApiTest {

    private SearchApi searchApi;

    @Before
    public void setUp() throws Exception {
        searchApi = new SearchApi(RetrofitCreator.createRetrofit());
    }

    @Test
    public void testSearchText() throws Exception {
        {
            TestSubscriber<SearchChannel> subscriber = TestSubscriber.create();
            searchApi.searchText("", 1)
                    .subscribe(subscriber);

            List<Throwable> onErrorEvents = subscriber.getOnErrorEvents();
            assertThat(onErrorEvents.size(), is(greaterThan(0)));
        }

        {
            TestSubscriber<SearchChannel> subscriber = TestSubscriber.create();
            searchApi.searchText("kakao", 1)
                    .subscribe(subscriber);

            subscriber.assertCompleted();
            subscriber.assertNoErrors();
            subscriber.assertValueCount(1);
        }
    }
}