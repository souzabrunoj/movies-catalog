package com.moviecatalog.features.login.signup.ui.step

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.moviecatalog.core.designsystem.components.button.MovieButton
import com.moviecatalog.core.designsystem.components.divider.MovieDivider
import com.moviecatalog.core.designsystem.components.icon.MovieIcon
import com.moviecatalog.core.designsystem.components.input.MovieTextInput
import com.moviecatalog.core.designsystem.components.snackbar.MovieSnackbarAction
import com.moviecatalog.core.designsystem.components.snackbar.MovieSnackbarHost
import com.moviecatalog.core.designsystem.components.snackbar.rememberMovieSnackbarHostState
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.icons.MovieIcons
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.button.MovieButtonVariant
import com.moviecatalog.core.designsystem.tokens.divider.MovieDividerSize
import com.moviecatalog.core.designsystem.tokens.size.MovieCornerRadius
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.snackbar.MovieSnackbarDuration
import com.moviecatalog.core.designsystem.tokens.snackbar.MovieSnackbarVariant
import com.moviecatalog.core.designsystem.tokens.type.MovieIconColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.navigator.flow.state.collectDataAsState
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.core.navigator.step.StepNavigationOptions
import com.moviecatalog.features.login.generated.resources.Res
import com.moviecatalog.features.login.generated.resources.content_desc_hide_password
import com.moviecatalog.features.login.generated.resources.content_desc_show_password
import com.moviecatalog.features.login.generated.resources.login_password_placeholder
import com.moviecatalog.features.login.generated.resources.nav_app_title
import com.moviecatalog.features.login.generated.resources.navigation_back
import com.moviecatalog.features.login.generated.resources.signup_complete_registration
import com.moviecatalog.features.login.generated.resources.signup_confirm_password_label
import com.moviecatalog.features.login.generated.resources.signup_create_password_label
import com.moviecatalog.features.login.generated.resources.signup_rule_digit
import com.moviecatalog.features.login.generated.resources.signup_rule_letter
import com.moviecatalog.features.login.generated.resources.signup_rule_match
import com.moviecatalog.features.login.generated.resources.signup_rule_min_length
import com.moviecatalog.features.login.generated.resources.signup_rule_special
import com.moviecatalog.features.login.generated.resources.signup_saving
import com.moviecatalog.features.login.generated.resources.signup_screen_title
import com.moviecatalog.features.login.generated.resources.signup_terms_notice
import com.moviecatalog.features.login.generated.resources.signup_username_label
import com.moviecatalog.features.login.generated.resources.signup_username_placeholder
import com.moviecatalog.features.login.signup.domain.model.MoviePasswordRulesState
import com.moviecatalog.features.login.signup.ui.uiModel.MovieSignUpUiModel
import com.moviecatalog.features.login.signup.ui.uiModel.state.MovieSignUpFeedbackEvent
import com.moviecatalog.features.login.signup.ui.uiModel.state.MovieSignUpUiState
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

internal data object MovieCatalogSignUpStep : Step() {

    override val navigationOptions: StepNavigationOptions
        @Composable
        get() {
            val title = stringResource(Res.string.nav_app_title)
            val back = stringResource(Res.string.navigation_back)
            return remember(title, back) {
                StepNavigationOptions(
                    title = title,
                    showNavigationAction = true,
                    navigationContentDescription = back,
                )
            }
        }

    @Composable
    override fun Content() {
        val uiModel = koinViewModel<MovieSignUpUiModel>()
        val data by uiModel.collectDataAsState()
        val navigator = LocalFlowNavigator.current

        StepContent(
            data = data,
            onUsernameChange = uiModel::onUsernameChange,
            onPasswordChange = uiModel::onPasswordChange,
            onConfirmPasswordChange = uiModel::onConfirmPasswordChange,
            onTogglePasswordVisible = uiModel::togglePasswordVisible,
            onToggleConfirmPasswordVisible = uiModel::toggleConfirmPasswordVisible,
            onCompleteRegistration = uiModel::completeRegistration,
            onConsumeFeedback = uiModel::consumeFeedback,
            onAfterSuccessfulRegistration = { navigator.pop() },
        )
    }

    @Composable
    internal fun StepContent(
        data: MovieSignUpUiState = MovieSignUpUiState(),
        onUsernameChange: (String) -> Unit = {},
        onPasswordChange: (String) -> Unit = {},
        onConfirmPasswordChange: (String) -> Unit = {},
        onTogglePasswordVisible: () -> Unit = {},
        onToggleConfirmPasswordVisible: () -> Unit = {},
        onCompleteRegistration: () -> Unit = {},
        onConsumeFeedback: () -> Unit = {},
        onAfterSuccessfulRegistration: () -> Unit = {},
    ) {
        SignUpScreen(
            data = data,
            onUsernameChange = onUsernameChange,
            onPasswordChange = onPasswordChange,
            onConfirmPasswordChange = onConfirmPasswordChange,
            onTogglePasswordVisible = onTogglePasswordVisible,
            onToggleConfirmPasswordVisible = onToggleConfirmPasswordVisible,
            onCompleteRegistration = onCompleteRegistration,
            onConsumeFeedback = onConsumeFeedback,
            onAfterSuccessfulRegistration = onAfterSuccessfulRegistration,
        )
    }
}

@Composable
private fun SignUpScreen(
    data: MovieSignUpUiState,
    onUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onTogglePasswordVisible: () -> Unit,
    onToggleConfirmPasswordVisible: () -> Unit,
    onCompleteRegistration: () -> Unit,
    onConsumeFeedback: () -> Unit,
    onAfterSuccessfulRegistration: () -> Unit,
) {
    val semantic = MovieTheme.colors
    val scroll = rememberScrollState()
    val rules = data.passwordRules
    val snackbarHostState = rememberMovieSnackbarHostState()
    val screenTitle = stringResource(Res.string.signup_screen_title)
    val usernameLabel = stringResource(Res.string.signup_username_label)
    val usernamePlaceholder = stringResource(Res.string.signup_username_placeholder)
    val createPasswordLabel = stringResource(Res.string.signup_create_password_label)
    val confirmPasswordLabel = stringResource(Res.string.signup_confirm_password_label)
    val passwordPlaceholder = stringResource(Res.string.login_password_placeholder)
    val completeRegistration = stringResource(Res.string.signup_complete_registration)
    val saving = stringResource(Res.string.signup_saving)
    val termsNotice = stringResource(Res.string.signup_terms_notice)
    val hidePassword = stringResource(Res.string.content_desc_hide_password)
    val showPassword = stringResource(Res.string.content_desc_show_password)

    LaunchedEffect(data.feedbackEvent) {
        when (val event = data.feedbackEvent) {
            is MovieSignUpFeedbackEvent.Success -> {
                snackbarHostState.show(
                    message = event.message,
                    duration = MovieSnackbarDuration.Short,
                    variant = MovieSnackbarVariant.Success,
                )
                onConsumeFeedback()
                onAfterSuccessfulRegistration()
            }

            is MovieSignUpFeedbackEvent.UsernameTaken -> {
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
    var confirmTf by remember { mutableStateOf(TextFieldValue()) }

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
    LaunchedEffect(data.confirmPassword) {
        if (confirmTf.text != data.confirmPassword) {
            confirmTf = TextFieldValue(
                text = data.confirmPassword,
                selection = TextRange(data.confirmPassword.length),
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(semantic.backgroundBody),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scroll)
                .padding(horizontal = MovieSpace.Medium, vertical = MovieSpace.Medium),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ) {
                MovieText(
                    text = screenTitle,
                    variant = MovieTextVariant.HeadingLarge(FontWeight.Bold),
                    contentColor = MovieTextColor.High,
                    modifier = Modifier.fillMaxHeight()
                )
            }
            Spacer(Modifier.height(MovieSpace.Small))
            MovieDivider(size = MovieDividerSize.Medium)
            Spacer(Modifier.height(MovieSpace.Large))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(MovieCornerRadius.Large))
                    .background(semantic.backgroundSurface)
                    .padding(MovieSpace.Large),
                verticalArrangement = Arrangement.spacedBy(MovieSpace.Medium),
            ) {
                MovieTextInput(
                    value = usernameTf,
                    onValueChange = {
                        usernameTf = it
                        onUsernameChange(it.text)
                    },
                    label = usernameLabel,
                    placeholder = usernamePlaceholder,
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
                    label = createPasswordLabel,
                    placeholder = passwordPlaceholder,
                    leading = { MovieIcon(icon = MovieIcons.Lock) },
                    visualTransformation = if (data.passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailing = {
                        PasswordVisibilityToggle(
                            visible = data.passwordVisible,
                            hideLabel = hidePassword,
                            showLabel = showPassword,
                            onToggle = onTogglePasswordVisible,
                        )
                    },
                )
                MovieTextInput(
                    value = confirmTf,
                    onValueChange = {
                        confirmTf = it
                        onConfirmPasswordChange(it.text)
                    },
                    label = confirmPasswordLabel,
                    placeholder = passwordPlaceholder,
                    leading = { MovieIcon(icon = MovieIcons.Lock) },
                    visualTransformation = if (data.confirmPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    trailing = {
                        PasswordVisibilityToggle(
                            visible = data.confirmPasswordVisible,
                            hideLabel = hidePassword,
                            showLabel = showPassword,
                            onToggle = onToggleConfirmPasswordVisible,
                        )
                    },
                )

                PasswordRequirementsCard(
                    rules = rules,
                    modifier = Modifier.fillMaxWidth(),
                    ruleMinLength = stringResource(Res.string.signup_rule_min_length),
                    ruleDigit = stringResource(Res.string.signup_rule_digit),
                    ruleLetter = stringResource(Res.string.signup_rule_letter),
                    ruleSpecial = stringResource(Res.string.signup_rule_special),
                    ruleMatch = stringResource(Res.string.signup_rule_match),
                )

                data.formErrorMessage?.let { msg ->
                    MovieText(
                        text = msg,
                        variant = MovieTextVariant.TextSmall(),
                        contentColor = MovieTextColor.Brand,
                    )
                }

                val canCompleteRegistration = rules.allSatisfied
                MovieButton(
                    text = completeRegistration,
                    onClick = onCompleteRegistration,
                    variant = MovieButtonVariant.Primary,
                    modifier = Modifier.fillMaxWidth(),
                    enabled = canCompleteRegistration,
                    isLoading = data.isSubmitting,
                    loadingText = saving,
                )
            }

            Spacer(Modifier.height(MovieSpace.Large))
            MovieText(
                text = termsNotice,
                variant = MovieTextVariant.TextSmall(),
                contentColor = MovieTextColor.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MovieSpace.Small),
            )
        }
        MovieSnackbarHost(hostState = snackbarHostState)
    }
}

@Composable
private fun PasswordVisibilityToggle(
    visible: Boolean,
    hideLabel: String,
    showLabel: String,
    onToggle: () -> Unit,
) {
    val icon = if (visible) MovieIcons.VisibilityOff else MovieIcons.Visibility
    Box(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onToggle,
        ),
    ) {
        MovieIcon(
            icon = icon,
            contentDescription = if (visible) hideLabel else showLabel,
            modifier = Modifier.padding(MovieSpace.Small),
        )
    }
}

@Composable
private fun PasswordRequirementsCard(
    rules: MoviePasswordRulesState,
    modifier: Modifier = Modifier,
    ruleMinLength: String,
    ruleDigit: String,
    ruleLetter: String,
    ruleSpecial: String,
    ruleMatch: String,
) {
    val semantic = MovieTheme.colors
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(MovieCornerRadius.Small))
            .background(semantic.fillNeutral)
            .padding(MovieSpace.Medium),
        verticalArrangement = Arrangement.spacedBy(MovieSpace.Small),
    ) {
        RuleRow(ruleMinLength, rules.hasMinLength)
        RuleRow(ruleDigit, rules.hasDigit)
        RuleRow(ruleLetter, rules.hasLetter)
        RuleRow(ruleSpecial, rules.hasSpecialChar)
        RuleRow(ruleMatch, rules.hasPasswordsMatch)
    }
}

@Composable
private fun RuleRow(label: String, satisfied: Boolean) {
    val icon = if (satisfied) MovieIcons.CheckCircle else MovieIcons.Close
    val tint = if (satisfied) MovieIconColor.Positive else MovieIconColor.Medium
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MovieSpace.Small),
    ) {
        MovieIcon(
            icon = icon,
            contentDescription = null,
            tint = tint,
        )
        MovieText(
            text = label,
            variant = MovieTextVariant.TextSmall(),
            contentColor = if (satisfied) MovieTextColor.High else MovieTextColor.Medium,
        )
    }
}
