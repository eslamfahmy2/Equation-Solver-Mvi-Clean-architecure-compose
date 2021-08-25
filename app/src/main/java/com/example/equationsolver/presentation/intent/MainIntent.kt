package com.example.equationsolver.presentation.intent

sealed class MainIntent {

    data class Solve(val param: String,val  action: String,val  initDelay: String) : MainIntent()

    object FetchResult : MainIntent()


}