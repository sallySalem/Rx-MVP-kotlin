package com.example.sally.rxjavakotlin.ui.home

import com.example.sally.rxjavakotlin.data.model.RepositoryModel
import com.example.sally.rxjavakotlin.ui.base.BaseView


/**
 * Created by Sally Salem on 2/3/18.
 */
interface HomeView : BaseView {
    fun initRepositoryList(repositoriesList: ArrayList<RepositoryModel>)

    fun loadRepositoriesList()

    fun hideSwipeRefreshLayout()

    fun hideRepositoriesList()

    fun showErrorMessage()

    fun navigateToRepositoryDetails(repositoryModel: RepositoryModel)
}