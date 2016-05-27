package com.nobrain.samples.daumeimagesearch.home.presenter

interface HomePresenter {

    fun inputSearchText(searchText: String)

    fun unSubscribeSearch()

    fun onItemClick(position: Int)

    interface View {

        fun refresh()

        fun onMoveLink(link: String)
    }
}
