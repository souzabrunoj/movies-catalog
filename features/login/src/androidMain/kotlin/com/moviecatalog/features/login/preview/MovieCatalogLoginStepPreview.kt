package com.moviecatalog.features.login.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.features.login.navigation.MovieCatalogLoginStep

@Preview(showBackground = true, name = "Login")
@Composable
private fun MovieCatalogLoginStepPreview() {
    MovieTheme {
        MovieCatalogLoginStep.StepContent(onLogin = {}, onSignUp = {}, onForgotPassword = {},)
    }
}