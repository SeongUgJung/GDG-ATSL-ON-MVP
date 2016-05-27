package com.nobrain.samples.daumeimagesearch.home.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnTextChanged
import com.nobrain.samples.daumeimagesearch.R
import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapter
import com.nobrain.samples.daumeimagesearch.home.adapter.ImageAdapterDataView
import com.nobrain.samples.daumeimagesearch.home.dagger.DaggerHomeComponent
import com.nobrain.samples.daumeimagesearch.home.dagger.HomeModule
import com.nobrain.samples.daumeimagesearch.home.presenter.HomePresenter
import com.nobrain.samples.daumeimagesearch.views.OnRecyclerItemClickListener
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomePresenter.View {

    @Inject
    lateinit var homePresenter: HomePresenter

    @Inject
    lateinit var imageAdapterDataView: ImageAdapterDataView

    @BindView(R.id.et_home_search)
    lateinit var etSearch: EditText

    @BindView(R.id.rv_home_search_result)
    lateinit var rvSearchResult: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val adapter = ImageAdapter(this@HomeActivity)

        DaggerHomeComponent.builder().homeModule(HomeModule(this, adapter)).build().inject(this)

        ButterKnife.bind(this)

        rvSearchResult.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@HomeActivity)
        }

        imageAdapterDataView.setOnRecyclerItemClickListener(object : OnRecyclerItemClickListener {
            override fun onItemClick(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, position: Int) {
                homePresenter.onItemClick(position)
            }
        })
    }

    override fun onDestroy() {
        homePresenter.unSubscribeSearch()
        super.onDestroy()
    }

    @OnTextChanged(R.id.et_home_search)
    internal fun onChangedSearchText(text: CharSequence) {
        homePresenter.inputSearchText(text.toString())
    }

    override fun refresh() {
        imageAdapterDataView.refresh()
    }

    override fun onMoveLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(intent)
    }
}
