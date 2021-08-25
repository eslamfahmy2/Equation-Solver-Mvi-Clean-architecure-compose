package com.example.equationsolver.data.worker

import androidx.lifecycle.LiveData
import androidx.work.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MathWorkManager @Inject constructor(
    private val workManager: WorkManager,
) {
    fun workRequest(vararg param: Float, operator: String, delay: Long) {

        val inputData = workDataOf(EQUATION_PARAMS to param, EQUATION_OPERATOR to operator)

        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<MathWorker>()
            .setInputData(inputData)
            .setInitialDelay(delay, TimeUnit.SECONDS)
            .addTag(EQUATION_TAG)
            .build()

        workManager.enqueue(uploadWorkRequest)


    }

    fun listWorkersInfo(): LiveData<MutableList<WorkInfo>> = workManager.getWorkInfosByTagLiveData(
        EQUATION_TAG
    )

    fun cancelWorks() = workManager.cancelAllWork()


}