package com.example.equationsolver.data.engine

import android.util.Log
import com.example.equationsolver.presentation.LOGGER

object Engine {
    fun solve(vararg params: Float, operator: String): Float {
        Log.d(LOGGER, "solve: pa "+ params.size + " op "+ operator)
        return when (operator) {
            Operator.SUM.toString() -> params.reduce { acc, fl -> acc + fl }
            Operator.SUB.toString() -> params.reduce { acc, fl -> acc - fl }
            Operator.DIV.toString() -> params.reduce { acc, fl -> acc / fl }
            Operator.MUL.toString() -> params.reduce { acc, fl -> acc * fl }
            else -> 0f
        }

    }

}