package com.example.equationsolver.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.equationsolver.data.worker.RESULT
import javax.inject.Inject


private const val TAG = "MainActivityView"

class UseCases @Inject constructor(
    private val repository: EquationRepository
) {

     fun list(): LiveData<List<ResultModel>> =
        Transformations.map(repository.listWorks()) { it ->
            return@map it.map {
                ResultModel(
                    it.outputData.keyValueMap[RESULT]?.toString() ?: "No Result Yet",
                    it.state.toString(),
                )
            }
        }


     fun inset(vararg param: Float, operator: String, delay: Long) =
        repository.workRequest(param = param, operator = operator, delay = delay)


}

