package com.example.equationsolver.presentation.viewstate

import com.example.equationsolver.domain.ResultModel

sealed class MainState {

    object Loading : MainState()
    data class Result(val result: List<ResultModel>) : MainState()
    data class Error(val error: String) : MainState()

}