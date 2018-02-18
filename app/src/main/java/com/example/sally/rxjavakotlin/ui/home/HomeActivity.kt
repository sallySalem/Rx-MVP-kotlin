package com.example.sally.rxjavakotlin.ui.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.View.*
import android.widget.Toast
import com.example.sally.rxjavakotlin.R
import com.example.sally.rxjavakotlin.data.model.RepositoryModel
import com.example.sally.rxjavakotlin.ui.base.BaseActivity
import com.example.sally.rxjavakotlin.ui.details.DetailsActivity
import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeView {
    @Inject
    lateinit var homePresenter: HomePresenter
    var repositoryAdapter: RepositoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView();
        homePresenter.onCreate();
    }


    private fun initView() {
        swipe_container.setColorSchemeColors(
                ContextCompat.getColor(this, android.R.color.holo_blue_dark),
                ContextCompat.getColor(this, android.R.color.holo_red_dark),
                ContextCompat.getColor(this, android.R.color.holo_green_dark),
                ContextCompat.getColor(this, android.R.color.holo_orange_dark))
        swipe_container.isRefreshing = true
        swipe_container.setOnRefreshListener(pullToRefreshListener)
    }

    override fun initRepositoryList(repositoriesList: ArrayList<RepositoryModel>) {
        //init recyclerView
        repositoryAdapter = RepositoryAdapter(repositoriesList, itemClickListener)
        val linearLayoutManager = LinearLayoutManager(this)
        rv_repositories_list.layoutManager = linearLayoutManager
        rv_repositories_list.adapter = repositoryAdapter
    }

    override fun loadRepositoriesList() {
        tv_empty_data.visibility = GONE
        rv_repositories_list.visibility = VISIBLE
        repositoryAdapter?.notifyDataSetChanged()
    }

    override fun hideSwipeRefreshLayout() {
        swipe_container.isRefreshing = false
    }

    override fun hideRepositoriesList() {
        rv_repositories_list.visibility = View.GONE
        tv_empty_data.visibility = View.VISIBLE
    }

    override fun showErrorMessage() {
        Toast.makeText(this, R.string.mesg_server_error, Toast.LENGTH_LONG).show()
    }

    override fun navigateToRepositoryDetails(repositoryModel: RepositoryModel) {
        DetailsActivity.open(this, repositoryModel)
    }

    private val pullToRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        homePresenter.onRepositoryListRefresh()
    }

    /*
    Listener to handle RecyclerView item select
     */
    private val itemClickListener = object : OnItemSelectedListener {
        override fun onClick(view: View, position: Int) {
            homePresenter.onItemClick(position)
        }
    }

    override fun initializeDagger() {
        AndroidInjection.inject(this)
    }

    override fun initializePresenter() {
        super.presenter = homePresenter
        homePresenter.view = this
    }

    override var layoutId: Int = R.layout.activity_home

    companion object {
        fun open(activity: Activity) {
            val intent = Intent(activity, HomeActivity::class.java)
            activity.startActivity(intent)
        }
    }
}
