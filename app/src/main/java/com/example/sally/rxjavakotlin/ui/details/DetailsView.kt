package com.example.sally.rxjavakotlin.ui.details

import com.example.sally.rxjavakotlin.data.model.RepositoryOwnerModel
import com.example.sally.rxjavakotlin.ui.base.BaseView


/**
 * Created by Sally Salem on 2/4/18.
 */
interface DetailsView : BaseView {

    fun initOwnerView(ownerInfo: RepositoryOwnerModel)

    fun hideProgressbar()

    fun hideEmptyDataView()

    fun showEmptyDataView()

    fun showErrorMessage()
}