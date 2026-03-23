package com.moviecatalog.features.login.auth.ui.step

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.moviecatalog.core.designsystem.components.button.MovieButton
import com.moviecatalog.core.designsystem.components.icon.MovieIcon
import com.moviecatalog.core.designsystem.components.input.MovieTextInput
import com.moviecatalog.core.designsystem.components.snackbar.MovieSnackbarAction
import com.moviecatalog.core.designsystem.components.snackbar.MovieSnackbarHost
import com.moviecatalog.core.designsystem.components.snackbar.rememberMovieSnackbarHostState
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.icons.MovieIcons
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.button.MovieButtonVariant
import com.moviecatalog.core.designsystem.tokens.size.MovieCornerRadius
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.snackbar.MovieSnackbarDuration
import com.moviecatalog.core.designsystem.tokens.snackbar.MovieSnackbarVariant
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.core.navigator.HomeDestination
import com.moviecatalog.core.navigator.LoginDestination
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.navigator.flow.state.collectDataAsState
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.core.navigator.step.StepNavigationOptions
import com.moviecatalog.features.login.auth.ui.uiModel.MovieLoginUiModel
import com.moviecatalog.features.login.auth.ui.uiModel.state.MovieLoginFeedbackEvent
import com.moviecatalog.features.login.auth.ui.uiModel.state.MovieLoginUiState
import org.koin.compose.viewmodel.koinViewModel

internal object MovieCatalogLoginStep : Step() {

    override val navigationOptions: StepNavigationOptions
        @Composable
        get() = remember {
            StepNavigationOptions(
                title = "CINEGRAPH",
                showNavigationAction = false,
            )
        }

    @Composable
    override fun Content() {
        val uiModel = koinViewModel<MovieLoginUiModel>()
        val data by uiModel.collectDataAsState()
        val navigator = LocalFlowNavigator.current

        LoginScreen(
            data = data,
            onUsernameChange = uiModel::onUsernameChange,
            onPasswordChange = uiModel::onPasswordChange,
            onLogin = uiModel::login,
            onConsumeFeedback = uiModel::consumeFeedback,
            onNavigateHome = { navigator.replaceAll(HomeDestination.Home) },
            onSignUp = { navigator.push(LoginDestination.SignUp) },
            onForgotPassword = { },
        )
    }

    @Composable
    internal fun StepContent(
        data: MovieLoginUiState = MovieLoginUiState(),
        onUsernameChange: (String) -> Unit = {},
        onPasswordChange: (String) -> Unit = {},
        onLogin: () -> Unit = {},
        onConsumeFeedback: () -> Unit = {},
        onNavigateHome: () -> Unit = {},
        onSignUp: () -> Unit = {},
        onForgotPassword: () -> Unit = {},
    ) {
        LoginScreen(
            data = data,
            onUsernameChange = onUsernameChange,
            onPasswordChange = onPasswordChange,
            onLogin = onLogin,
            onConsumeFeedback = onConsumeFeedback,
            onNavigateHome = onNavigateHome,
            onSignUp = onSignUp,
            onForgotPassword = onForgotPassword,
        )
    }
}

@Composable
private fun LoginScreen(
    data: MovieLoginUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onConsumeFeedback: () -> Unit,
    onNavigateHome: () -> Unit,
    onSignUp: () -> Unit,
    onForgotPassword: () -> Unit,
) {
    val semantic = MovieTheme.colors
    val scroll = rememberScrollState()
    val snackbarHostState = rememberMovieSnackbarHostState()

    LaunchedEffect(data.feedbackEvent) {
        when (val event = data.feedbackEvent) {
            MovieLoginFeedbackEvent.NavigateHome -> {
                onNavigateHome()
                onConsumeFeedback()
            }

            is MovieLoginFeedbackEvent.UserNotFound -> {
                snackbarHostState.show(
                    message = event.message,
                    duration = MovieSnackbarDuration.Long,
                    variant = MovieSnackbarVariant.Critical,
                    action =
                        MovieSnackbarAction.DismissIcon(
                            onClick = { snackbarHostState.dismiss() },
                        ),
                )
                onConsumeFeedback()
            }

            is MovieLoginFeedbackEvent.InvalidPassword -> {
                snackbarHostState.show(
                    message = event.message,
                    duration = MovieSnackbarDuration.Long,
                    variant = MovieSnackbarVariant.Critical,
                    action =
                        MovieSnackbarAction.DismissIcon(
                            onClick = { snackbarHostState.dismiss() },
                        ),
                )
                onConsumeFeedback()
            }

            is MovieLoginFeedbackEvent.UnexpectedError -> {
                snackbarHostState.show(
                    message = event.message,
                    duration = MovieSnackbarDuration.Long,
                    variant = MovieSnackbarVariant.Critical,
                    action =
                        MovieSnackbarAction.DismissIcon(
                            onClick = { snackbarHostState.dismiss() },
                        ),
                )
                onConsumeFeedback()
            }

            null -> Unit
        }
    }

    var usernameTf by remember { mutableStateOf(TextFieldValue()) }
    var passwordTf by remember { mutableStateOf(TextFieldValue()) }

    LaunchedEffect(data.username) {
        if (usernameTf.text != data.username) {
            usernameTf = TextFieldValue(
                text = data.username,
                selection = TextRange(data.username.length),
            )
        }
    }
    LaunchedEffect(data.password) {
        if (passwordTf.text != data.password) {
            passwordTf = TextFieldValue(
                text = data.password,
                selection = TextRange(data.password.length),
            )
        }
    }

    val canSubmit = data.username.isNotBlank() && data.password.isNotEmpty()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(semantic.backgroundBody),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(horizontal = MovieSpace.Medium, vertical = MovieSpace.XLarge),
        ) {
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
                    value = usernameTf,
                    onValueChange = {
                        usernameTf = it
                        onUsernameChange(it.text)
                    },
                    label = "Username",
                    placeholder = "Your username",
                    errorText = data.usernameErrorText,
                    leading = { MovieIcon(icon = MovieIcons.Person) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )
                MovieTextInput(
                    value = passwordTf,
                    onValueChange = {
                        passwordTf = it
                        onPasswordChange(it.text)
                    },
                    label = "Password",
                    placeholder = "••••••••",
                    errorText = data.passwordErrorText,
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
                    enabled = canSubmit && !data.isSubmitting,
                    isLoading = data.isSubmitting,
                    loadingText = "Signing in…",
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
        MovieSnackbarHost(hostState = snackbarHostState)
    }
}
