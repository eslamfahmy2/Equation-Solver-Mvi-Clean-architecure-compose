package com.example.equationsolver.presentation.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.equationsolver.R
import com.example.equationsolver.presentation.app.BaseApplication
import com.example.equationsolver.presentation.components.AppBar
import com.example.equationsolver.presentation.components.ResultCard
import com.example.equationsolver.presentation.intent.MainIntent
import com.example.equationsolver.presentation.theme.EquationSolverTheme
import com.example.equationsolver.presentation.viewmodel.EquationViewModel
import com.example.equationsolver.presentation.viewstate.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class EquationsListFragment : Fragment() {


    private val viewModel: EquationViewModel by viewModels()

    @Inject
    lateinit var application: BaseApplication


    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {

                EquationSolverTheme(
                    darkTheme = application.isDark()
                ) {
                    Scaffold(
                        topBar = {
                            AppBar(
                                navigateToSolver = {
                                    findNavController().navigate(R.id.action_equationsListFragment_to_solveEquationFragment)
                                },
                                onToggleTheme = {
                                    application.toggleTheme()
                                }
                            )
                        }
                    ) {

                        when (val state = viewModel.state.collectAsState().value) {
                            is MainState.Error -> {

                                Text(
                                    text = state.error,
                                    modifier = Modifier.padding(12.dp),
                                    style = TextStyle(
                                        color = Color.Black,
                                        fontSize = 30.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )


                            }

                            MainState.Loading -> {
                                CircularProgressIndicator(
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            is MainState.Result -> {

                                LazyColumn() {
                                    items(state.result) {
                                        ResultCard(
                                            resultModel = it,
                                        )
                                    }

                                }

                            }
                        }


                    }


                }

                lifecycleScope.launch {
                    viewModel.userIntent.send(MainIntent.FetchResult)
                }

            }
        }
    }


}



