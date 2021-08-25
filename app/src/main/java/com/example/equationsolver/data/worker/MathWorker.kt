package com.example.equationsolver.data.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.equationsolver.data.engine.Engine
import com.example.equationsolver.data.engine.Operator
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope

const val RESULT = "RESULT"
const val ERROR = "ERROR"
const val EQUATION_PARAMS = "EQUATION_PARAMS"
const val EQUATION_OPERATOR = "EQUATION_OPERATOR"
const val EQUATION_TAG = "EQUATION_TAG"

private const val TAG = "RecipeListFragment"


@HiltWorker
class MathWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {

            val equationParam = inputData.getFloatArray(EQUATION_PARAMS) ?: floatArrayOf()
            val equationOperator = inputData.getString(EQUATION_OPERATOR) ?: Operator.SUM.toString()


            val result = Engine.solve(*equationParam, operator = equationOperator)
            val data = workDataOf(RESULT to result)


            Result.success(data)
        } catch (e: Exception) {
            val data = workDataOf(ERROR to e.message)
            Result.failure(data)
        }

    }
}