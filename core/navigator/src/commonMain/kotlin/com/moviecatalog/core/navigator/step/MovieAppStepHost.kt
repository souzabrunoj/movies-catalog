package com.moviecatalog.core.navigator.step

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.moviecatalog.core.designsystem.components.appbar.MovieStepTopAppBar
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator

@Composable
internal fun MovieAppStepHost() {
    val flowNavigator = LocalFlowNavigator.current
    val navigator = LocalNavigator.currentOrThrow
    val step = (navigator.lastItem as StepBackedScreen).step
    val options = step.navigationOptions

    Column(Modifier.fillMaxSize()) {
        if (options.isVisible) {
            MovieStepTopAppBar(
                title = options.title,
                background = options.background,
                navigationAction = options.navigationAction,
                defaultBackEnabled = options.navigationAction == null &&
                    options.showNavigationAction &&
                    flowNavigator.canPop,
                onDefaultBackClick = { flowNavigator.pop() },
                navigationContentDescription = options.navigationContentDescription,
                primaryAction = options.primaryAction,
                secondaryAction = options.secondaryAction,
            )
        }
        Box(
            modifier = Modifier
                .weight(1f, fill = true)
                .fillMaxWidth(),
        ) {
            CurrentScreen()
        }
    }
}
