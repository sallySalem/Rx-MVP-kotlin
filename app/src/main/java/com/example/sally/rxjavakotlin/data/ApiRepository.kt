package com.example.sally.rxjavakotlin.data

import com.example.sally.rxjavakotlin.data.model.RepositoryModel
import com.example.sally.rxjavakotlin.data.model.RepositoryOwnerModel
import com.example.sally.rxjavakotlin.data.service.RepositoriesService
import io.reactivex.Single
import retrofit2.Retrofit
import java.util.ArrayList
import javax.inject.Inject


/**
 * Created by Sally Salem on 2/4/18.
 */
class ApiRepository @Inject constructor(retrofitService: Retrofit) {

    private val repositoriesService = retrofitService.create(RepositoriesService::class.java)

    fun getRepositoriesList(): Single<ArrayList<RepositoryModel>> {
        return repositoriesService.getRepositoriesList()
    }

    fun getRepositoryOwner(loginName: String): Single<RepositoryOwnerModel> {
        return repositoriesService.getRepositoryOwner(loginName)
    }
}