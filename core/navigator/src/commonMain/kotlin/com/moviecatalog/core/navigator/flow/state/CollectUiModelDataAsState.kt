package com.moviecatalog.core.navigator.flow.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.uimodel.UiMode
import com.moviecatalog.core.uimodel.UiModel
import com.moviecatalog.core.uimodel.UiModelState
import com.moviecatalog.core.uimodel.UiState as UiModelSnapshot

@Composable
public fun <T : UiModelState> UiModel<T>.collectDataAsState(): State<T> {
    val full: UiModelSnapshot<T> by this.state.collectAsStateWithLifecycle()
    val flowNavigator = LocalFlowNavigator.current

    val dataState: MutableState<T> = remember { mutableStateOf(full.data) }

    LaunchedEffect(full) {
        flowNavigator.reportStepUiMode(full.mode)
        dataState.value = full.data
    }

    DisposableEffect(Unit) {
        onDispose {
            flowNavigator.reportStepUiMode(UiMode.Content)
        }
    }

    return dataState
}
