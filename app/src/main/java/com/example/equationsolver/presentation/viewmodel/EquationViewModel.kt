package com.example.equationsolver.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.equationsolver.data.engine.Operator
import com.example.equationsolver.domain.UseCases
import com.example.equationsolver.presentation.LOGGER
import com.example.equationsolver.presentation.intent.MainIntent
import com.example.equationsolver.presentation.viewstate.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EquationViewModel @Inject constructor(
    private val useCases: UseCases

) : ViewModel() {
    //input
    val userIntent = Channel<MainIntent>(Channel.UNLIMITED)

    //Output
    private val _state = MutableStateFlow<MainState>(MainState.Loading)
    val state: StateFlow<MainState> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect { it ->
                Log.d(LOGGER, "handleIntent: " + it.toString())
                when (it) {
                    is MainIntent.FetchResult -> listEquationResults()
                    is MainIntent.Solve -> solveEquation(it.param, it.action, it.initDelay)
                }
            }
        }
    }

    private fun solveEquation(param: String, action: String, initDelay: String) {
        viewModelScope.launch {
            val delay = initDelay.ifEmpty { 0 }.toString().toLong()
            val operator = action.ifEmpty { Operator.SUM.toString() }.toString()
            val params = if (param.isEmpty())  floatArrayOf() else param.split(" ").filter { it.isNotEmpty() }.map { it.toFloat() }.toFloatArray()
            useCases.inset(param = params, operator = operator, delay = delay)
        }

    }

    private fun listEquationResults() {

        viewModelScope.launch {
            _state.value = MainState.Loading
            useCases.list().observeForever {
                _state.value = try {
                    MainState.Result(it)
                } catch (e: Exception) {
                    MainState.Error(e.localizedMessage ?: "")
                }
            }
        }
    }


}