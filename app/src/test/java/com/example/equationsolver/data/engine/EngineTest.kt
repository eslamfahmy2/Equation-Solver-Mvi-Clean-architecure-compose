package com.example.equationsolver.data.engine

import org.junit.Assert
import org.junit.Test

class EngineTest {



    @Test
    fun addition_isCorrect() {
        val result = Engine.solve(1f , 2f , 3f , operator = Operator.SUM)
        Assert.assertEquals(6f, result)
    }

    @Test
    fun `multiplication isCorrect`() {
        val result = Engine.solve(2f , 3f , 4f , operator = Operator.MUL)
        Assert.assertEquals(24f, result)
    }

    @Test
    fun `subtraction isCorrect`() {
        val result = Engine.solve(2f , 3f , 4f , operator = Operator.SUB)
        Assert.assertEquals(-5f, result)
    }

    @Test
    fun `division isCorrect`() {
        val result = Engine.solve(2f , 3f , 4f , operator = Operator.DIV)
        Assert.assertEquals(0.16666667f, result)
    }

    @Test
    fun `Infinity isCorrect`() {
        val result = Engine.solve(2f , 0f , 4f , operator = Operator.DIV)
        Assert.assertEquals(Float.POSITIVE_INFINITY, result)
    }


}