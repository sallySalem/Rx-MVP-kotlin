 package com.example.sally.rxjavakotlin.ui.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.makeText
import com.example.sally.rxjavakotlin.R
import com.example.sally.rxjavakotlin.data.model.RepositoryModel
import com.example.sally.rxjavakotlin.data.model.RepositoryOwnerModel
import com.example.sally.rxjavakotlin.ui.base.BaseActivity
import com.example.sally.rxjavakotlin.utils.KEY_REPOSITORY_MODEL
import com.example.sally.rxjavakotlin.utils.formatDateTime
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjection
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_details.*
import java.lang.String.valueOf

 class DetailsActivity : BaseActivity(), DetailsView {
     @Inject
     lateinit var detailsPresenter: DetailsPresenter

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)

         var extras = intent.extras
         if (extras != null) {
             //init details view
             val repositoryModel = extras.getParcelable<Parcelable>(KEY_REPOSITORY_MODEL) as RepositoryModel?
             repositoryModel?.let {
                 detailsPresenter.onCreate(repositoryModel);
                 initRepositoryDetailsView(repositoryModel)
             }
         }
     }

     override fun initOwnerView(ownerInfo: RepositoryOwnerModel) {
         //init owner info
         Picasso.with(this).load(ownerInfo.avatarUrl).placeholder(R.mipmap.ic_launcher).into(iv_owner_avatar)
         tv_owner_name.text = ownerInfo.ownerName
         tv_owner_blog.text = ownerInfo.ownerBlog
         tv_owner_email.text  = ownerInfo.ownerEmail
     }

     override fun hideProgressbar() {
         pb_owner_data.visibility = GONE
     }

     override fun hideEmptyDataView() {
         tv_empty_data.visibility = GONE
     }

     override fun showEmptyDataView() {
         tv_empty_data.visibility = VISIBLE
     }

     override fun showErrorMessage() {
         makeText(this, R.string.mesg_server_error, LENGTH_LONG).show()
     }

     override fun initializeDagger() {
         AndroidInjection.inject(this)
     }

     override fun initializePresenter() {
         super.presenter = detailsPresenter
         detailsPresenter.view = this;
     }

     override var layoutId: Int = R.layout.activity_details;


     private fun initRepositoryDetailsView(repositoryModel: RepositoryModel) {
         //init repository info
         tv_repository_name_details.text = repositoryModel.name
         tv_repository_description_details.text = repositoryModel.description
         tv_repository_language.text = repositoryModel.language
         tv_repository_fork.text = valueOf(repositoryModel.isFork)
         tv_repository_updated_time.text = repositoryModel.updatedAt.formatDateTime()
     }

     companion object {
         fun open(activity: Activity, repositoryModel: RepositoryModel) {
             val repositoryDetailsIntent = Intent(activity, DetailsActivity::class.java)
             repositoryDetailsIntent.putExtra(KEY_REPOSITORY_MODEL, repositoryModel)
             activity.startActivity(repositoryDetailsIntent)
         }
     }
}
