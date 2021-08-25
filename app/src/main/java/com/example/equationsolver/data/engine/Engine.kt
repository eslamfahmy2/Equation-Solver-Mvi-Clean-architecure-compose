package com.example.equationsolver.data.engine

object Engine {
    fun solve(vararg params: Float, operator: String): Float {
        return when (operator) {
            Operator.SUM.toString() -> params.reduce { acc, fl -> acc + fl }
            Operator.SUB.toString() -> params.reduce { acc, fl -> acc - fl }
            Operator.DIV.toString() -> params.reduce { acc, fl -> acc / fl }
            Operator.MUL.toString() -> params.reduce { acc, fl -> acc * fl }
            else -> 0f
        }

    }

}