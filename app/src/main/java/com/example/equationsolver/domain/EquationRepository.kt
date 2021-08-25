package com.example.equationsolver.domain

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo

interface EquationRepository {

    fun workRequest(vararg param: Float, operator: String, delay: Long)

    fun listWorks(): LiveData<MutableList<WorkInfo>>

}