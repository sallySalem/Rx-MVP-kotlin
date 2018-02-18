package com.example.sally.rxjavakotlin.ui.details

import com.example.sally.rxjavakotlin.data.model.RepositoryModel
import com.example.sally.rxjavakotlin.ui.base.BasePresenter
import com.example.sally.rxjavakotlin.usecase.RepositoryUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


/**
 * Created by Sally Salem on 2/4/18.
 */
class DetailsPresenter @Inject constructor(val repositoryUseCase: RepositoryUseCase, compositeDisposable: CompositeDisposable) : BasePresenter<DetailsView>(compositeDisposable) {

    fun onCreate(repositoryModel: RepositoryModel) {
        //get full ownerInfo
        repositoryModel.ownerInfo?.login?.let { getRepositoryOwnerDetails(it) }
    }

    private fun getRepositoryOwnerDetails(loginName: String) {
        //call useCase
        compositeDisposable.add(repositoryUseCase.getRepositoryOwnerDetails(loginName)
                .subscribe({
                    view?.hideProgressbar()
                    if (it != null) {
                        view?.hideEmptyDataView()
                        view?.initOwnerView(it)
                    } else {
                        //handle successful response with empty data
                        view?.showEmptyDataView()
                    }
                }, {
                    //handle failure
                    view?.let {
                        it.hideProgressbar()
                        it.showEmptyDataView()
                        it.showErrorMessage()
                    }
                }))
    }
}