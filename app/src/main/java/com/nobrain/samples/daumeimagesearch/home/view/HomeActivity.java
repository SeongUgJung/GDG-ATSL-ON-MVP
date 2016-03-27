package com.nobrain.samples.daumeimagesearch.home.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.nobrain.samples.daumeimagesearch.R;
import com.nobrain.samples.daumeimagesearch.home.dagger.DaggerHomeComponent;
import com.nobrain.samples.daumeimagesearch.home.dagger.HomeModule;
import com.nobrain.samples.daumeimagesearch.home.presenter.HomePresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomePresenter.View {

    @Inject
    HomePresenter homePresenter;

    @Bind(R.id.et_home_search)
    EditText etSearch;

    @Bind(R.id.rv_home_search_result)
    RecyclerView rvSearchResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        DaggerHomeComponent.builder()
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);

        ButterKnife.bind(this);
    }
}
