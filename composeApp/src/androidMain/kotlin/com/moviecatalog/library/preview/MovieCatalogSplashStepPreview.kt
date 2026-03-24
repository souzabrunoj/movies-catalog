package com.moviecatalog.library.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.screens.splash.MovieCatalogSplashStep

@Preview(showBackground = true, name = "Splash — mid progress")
@Composable
private fun MovieCatalogSplashStepPreviewMid() {
    MovieTheme {
        MovieCatalogSplashStep.StepContent(progress = 0.45f)
    }
}

@Preview(showBackground = true, name = "Splash — almost complete")
@Composable
private fun MovieCatalogSplashStepPreviewAlmostDone() {
    MovieTheme {
        MovieCatalogSplashStep.StepContent(progress = 0.92f)
    }
}
