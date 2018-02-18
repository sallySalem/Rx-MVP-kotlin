package com.example.sally.rxjavakotlin.usecase

import com.example.sally.rxjavakotlin.data.ApiRepository
import com.example.sally.rxjavakotlin.data.model.RepositoryModel
import com.example.sally.rxjavakotlin.data.model.RepositoryOwnerModel
import com.example.sally.rxjavakotlin.utils.applyComputationScheduler
import io.reactivex.*
import javax.inject.Inject


/**
 * Created by Sally Salem on 2/4/18.
 */
class RepositoryUseCase @Inject constructor(private val repository: ApiRepository) {

    fun getRepositoriesList(): Single<java.util.ArrayList<RepositoryModel>> {
        return repository.getRepositoriesList()
                .applyComputationScheduler()
    }

    fun getRepositoryOwnerDetails(loginName: String): Single<RepositoryOwnerModel> {
        return repository.getRepositoryOwner(loginName)
                .applyComputationScheduler()
    }
}