package com.moviecatalog.features.login.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.moviecatalog.core.designsystem.components.button.MovieButton
import com.moviecatalog.core.designsystem.components.icon.MovieIcon
import com.moviecatalog.core.designsystem.components.input.MovieTextInput
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.icons.MovieIcons
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.button.MovieButtonVariant
import com.moviecatalog.core.designsystem.tokens.size.MovieCornerRadius
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.core.navigator.HomeDestination
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.navigator.step.Step

internal object MovieCatalogLoginStep : Step() {

    @Composable
    override fun Content() {
        val navigator = LocalFlowNavigator.current
        MovieCatalogLoginContent(
            onLogin = { navigator.replaceAll(HomeDestination.Home) },
            onSignUp = { },
            onForgotPassword = { },
        )
    }

    @Composable
    internal fun StepContent(onLogin: () -> Unit, onSignUp: () -> Unit, onForgotPassword: () -> Unit = {}) {
        MovieCatalogLoginContent(onLogin = onLogin, onSignUp = onSignUp, onForgotPassword = onForgotPassword)
    }
}

@Composable
private fun MovieCatalogLoginContent(
    onLogin: () -> Unit,
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit,
) {
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    val semantic = MovieTheme.colors
    val scroll = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(semantic.backgroundBody)
            .verticalScroll(scroll)
            .padding(horizontal = MovieSpace.Medium, vertical = MovieSpace.XLarge),
    ) {
        MovieText(
            text = "CINEGRAPH",
            variant = MovieTextVariant.DisplayMedium(FontWeight.Bold),
            contentColor = MovieTextColor.Brand,
        )
        Spacer(modifier = Modifier.height(MovieSpace.XLarge2))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(MovieCornerRadius.Large))
                .background(semantic.backgroundSurface)
                .padding(MovieSpace.Large),
            verticalArrangement = Arrangement.spacedBy(MovieSpace.Medium),
        ) {
            MovieText(
                text = "Welcome Back",
                variant = MovieTextVariant.HeadingLarge(FontWeight.Bold),
                contentColor = MovieTextColor.High,
            )
            MovieText(
                text = "Continue your curated cinematic journey.",
                variant = MovieTextVariant.TextMedium(),
                contentColor = MovieTextColor.Medium,
            )
            Spacer(modifier = Modifier.height(MovieSpace.Small))
            MovieTextInput(
                value = email,
                onValueChange = { email = it },
                label = "Email Address",
                placeholder = "name@domain.com",
                leading = { MovieIcon(icon = MovieIcons.Email) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            )
            MovieTextInput(
                value = password,
                onValueChange = { password = it },
                label = "Password",
                placeholder = "••••••••",
                leading = { MovieIcon(icon = MovieIcons.Lock) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                MovieText(
                    text = "Forgot Password?",
                    variant = MovieTextVariant.TextSmall(FontWeight.Medium),
                    contentColor = MovieTextColor.Brand,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onForgotPassword,
                    ),
                )
            }
            MovieButton(
                text = "LOGIN",
                onClick = onLogin,
                variant = MovieButtonVariant.Primary,
                modifier = Modifier.fillMaxWidth(),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MovieSpace.Medium),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                MovieText(
                    text = "New to CineGraph? ",
                    variant = MovieTextVariant.TextSmall(),
                    contentColor = MovieTextColor.Medium,
                    textAlign = TextAlign.Center,
                )
                MovieText(
                    text = "Create an account",
                    variant = MovieTextVariant.TextSmall(FontWeight.Bold),
                    contentColor = MovieTextColor.Brand,
                    modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onSignUp,
                    ),
                )
            }
        }
    }
}
