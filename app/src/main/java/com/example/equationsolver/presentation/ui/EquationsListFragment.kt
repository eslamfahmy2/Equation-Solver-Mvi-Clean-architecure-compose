package com.example.equationsolver.presentation.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.equationsolver.BaseApplication
import com.example.equationsolver.data.engine.Operator
import com.example.equationsolver.presentation.components.AppBar
import com.example.equationsolver.presentation.components.ResultCard
import com.example.equationsolver.presentation.theme.EquationSolverTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "RecipeListFragment"

@AndroidEntryPoint
class EquationsListFragment : Fragment() {


    private val viewModel: EquationListViewModel by viewModels()

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

                //  viewModel.insetEquation(1f, 2f, operator = Operator.SUM.name)


                val result = viewModel.equationResults.observeAsState()

                EquationSolverTheme(
                    darkTheme = application.isDark()
                ) {

                    Scaffold(
                        topBar = {
                            AppBar(
                                navigateToSolver = {
                                    viewModel.insetEquation(
                                        2f,
                                        3f,
                                        4f,
                                        operator = Operator.SUM.name,
                                        delay = 10
                                    )
                                },
                                onToggleTheme = {
                                    application.toggleTheme()
                                }
                            )
                        }
                    ) {


                        LazyColumn() {
                            result.value?.let { it ->
                                Log.d(TAG, "onCreateView: " + it.count())
                                items(it) {
                                    ResultCard(
                                        resultModel = it,
                                    )
                                }
                            }
                        }


                    }


                }

            }
        }
    }


}



