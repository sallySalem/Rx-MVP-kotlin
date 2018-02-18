package com.example.sally.rxjavakotlin.data.service

import com.example.sally.rxjavakotlin.data.model.RepositoryModel
import com.example.sally.rxjavakotlin.data.model.RepositoryOwnerModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.ArrayList


/**
 * Created by Sally Salem on 2/4/18.
 */
interface RepositoriesService {
    @GET("users/sallysalem/repos")
    fun getRepositoriesList(): Single<ArrayList<RepositoryModel>>

    @GET("users/{loginName}")
    fun getRepositoryOwner(@Path("loginName") loginName: String): Single<RepositoryOwnerModel>
}