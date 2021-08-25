package com.example.equationsolver.data.repository

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo
import com.example.equationsolver.data.worker.MathWorkManager
import com.example.equationsolver.domain.EquationRepository
import javax.inject.Inject

class EquationRepositoryImpl @Inject constructor(
    private val workManager: MathWorkManager
) : EquationRepository {


    override fun workRequest(vararg param: Float, operator: String , delay : Long) {
        workManager.workRequest(param = param, operator = operator , delay = delay)
    }

    override fun listWorks(): LiveData<MutableList<WorkInfo>> {
        return workManager.listWorkersInfo()
    }

    override fun cancelWorks() {
        workManager.cancelWorks()
    }

}