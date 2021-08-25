package com.example.equationsolver.presentation.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.equationsolver.data.engine.Operator
import com.example.equationsolver.presentation.components.DropDownList
import com.example.equationsolver.presentation.components.Header
import com.example.equationsolver.presentation.intent.MainIntent
import com.example.equationsolver.presentation.viewmodel.EquationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SolveEquationFragment : Fragment() {

    private val viewModel: EquationViewModel by viewModels()

    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {


                Scaffold(
                    topBar = {
                        Header(
                            title = "Insert equation",
                            navigateBack = {
                                findNavController().popBackStack()
                            },

                            )
                    }
                ) {


                    Column(modifier = Modifier.fillMaxWidth()) {

                        val query = remember { mutableStateOf(String()) } // initial value
                        val action =
                            remember { mutableStateOf(Operator.SUM.toString()) } // initial value
                        val initDelay = remember { mutableStateOf(String()) } // initial value

                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .padding(12.dp),
                            value = query.value,
                            onValueChange = {
                                query.value = it
                            },
                            label = {
                                Text(text = "Enter Equation param separate by space ")
                            },
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            textStyle = TextStyle(
                                color = MaterialTheme.colors.onSurface,
                                fontSize = 12.sp
                            ),

                            )

                        DropDownList(
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .padding(12.dp),
                            selected = {
                                action.value = it
                            }
                        )


                        TextField(
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .padding(12.dp),
                            value = initDelay.value,
                            onValueChange = {
                                initDelay.value = it
                            },
                            label = {
                                Text(text = "Delay in seconds ")
                            },
                            keyboardOptions = KeyboardOptions(
                                autoCorrect = false,
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                            textStyle = TextStyle(
                                color = MaterialTheme.colors.onSurface,
                                fontSize = 12.sp
                            ),

                            )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth(.9f)
                                .padding(12.dp)
                                .background(
                                    color = MaterialTheme.colors.onSurface,
                                ),
                            onClick = {
                                lifecycleScope.launch {
                                    viewModel.userIntent.send(
                                        MainIntent.Solve(
                                            query.value,
                                            action.value,
                                            initDelay.value
                                        )
                                    )
                                }
                                findNavController().popBackStack()
                            },


                            ) {
                            Text(
                                text = "Solve",
                                modifier = Modifier
                                    .padding(12.dp)
                                    .fillMaxWidth()
                            )

                        }


                    }
                }
            }
        }
    }


}



