package com.example.equationsolver.presentation.ui

import androidx.lifecycle.ViewModel
import com.example.equationsolver.domain.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EquationListViewModel @Inject constructor(
    private val useCases: UseCases

) : ViewModel() {

    val equationResults get() = useCases.list()


    fun insetEquation(vararg param: Float, operator: String, delay: Long) {
        useCases.inset(param = param, operator = operator, delay = delay)
    }
}