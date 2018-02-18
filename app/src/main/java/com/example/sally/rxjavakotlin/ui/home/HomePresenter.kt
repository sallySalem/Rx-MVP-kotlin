package com.example.sally.rxjavakotlin.ui.home

import com.example.sally.rxjavakotlin.data.model.RepositoryModel
import com.example.sally.rxjavakotlin.ui.base.BasePresenter
import com.example.sally.rxjavakotlin.usecase.RepositoryUseCase
import io.reactivex.disposables.CompositeDisposable

import java.util.*
import javax.inject.Inject


/**
 * Created by Sally Salem on 2/3/18.
 */
class HomePresenter @Inject constructor(val repositoryUseCase: RepositoryUseCase, compositeDisposable: CompositeDisposable) : BasePresenter<HomeView>(compositeDisposable) {
    private var repositoriesList = ArrayList<RepositoryModel>()

    fun onCreate() {
        view?.initRepositoryList(repositoriesList)
        getRepositoriesList()
    }

    fun onRepositoryListRefresh() {
        getRepositoriesList()
    }

    fun onItemClick(position: Int) {
        view?.navigateToRepositoryDetails(repositoriesList[position])
    }

    private fun getRepositoriesList() {
        //Call backend to get repositories list
        compositeDisposable.add(repositoryUseCase.getRepositoriesList()
                .subscribe(
                        { responseResult ->
                            view?.hideSwipeRefreshLayout()
                            if (responseResult != null && responseResult.size > 0) {
                                repositoriesList.removeAll(repositoriesList)
                                repositoriesList.addAll(responseResult)
                                view?.loadRepositoriesList()
                            } else {
                                //handle successful case with empty response
                                view?.hideRepositoriesList()
                            }
                        },
                        { e ->
                            //handle failure
                            view?.let {
                                it.hideSwipeRefreshLayout()
                                it.hideRepositoriesList()
                                it.showErrorMessage()
                            }
                        }))
    }
}