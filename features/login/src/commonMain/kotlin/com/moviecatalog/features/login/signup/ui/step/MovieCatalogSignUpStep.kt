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
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.icons.MovieIcons
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.button.MovieButtonVariant
import com.moviecatalog.core.designsystem.tokens.divider.MovieDividerSize
import com.moviecatalog.core.designsystem.tokens.size.MovieCornerRadius
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.type.MovieIconColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.core.navigator.flow.navigator.LocalFlowNavigator
import com.moviecatalog.core.navigator.flow.state.collectDataAsState
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.core.navigator.step.StepNavigationOptions
import com.moviecatalog.features.login.signup.domain.model.MoviePasswordRulesState
import com.moviecatalog.features.login.signup.ui.uiModel.MovieSignUpUiModel
import com.moviecatalog.features.login.signup.ui.uiModel.state.MovieSignUpUiState
import org.koin.compose.viewmodel.koinViewModel

internal data object MovieCatalogSignUpStep : Step() {

    override val navigationOptions: StepNavigationOptions
        @Composable
        get() = remember {
            StepNavigationOptions(
                title = "CINEGRAPH",
                showNavigationAction = true,
                navigationContentDescription = "Back",
            )
        }

    @Composable
    override fun Content() {
        val uiModel = koinViewModel<MovieSignUpUiModel>()
        val data by uiModel.collectDataAsState()
        val navigator = LocalFlowNavigator.current

        SignUpScreen(
            data = data,
            onUsernameChange = uiModel::onUsernameChange,
            onPasswordChange = uiModel::onPasswordChange,
            onConfirmPasswordChange = uiModel::onConfirmPasswordChange,
            onTogglePasswordVisible = uiModel::togglePasswordVisible,
            onToggleConfirmPasswordVisible = uiModel::toggleConfirmPasswordVisible,
            onCompleteRegistration = {
                uiModel.completeRegistration { navigator.pop() }
            },
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
) {
    val semantic = MovieTheme.colors
    val scroll = rememberScrollState()
    val rules = data.passwordRules

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(semantic.backgroundBody)
            .verticalScroll(scroll)
            .padding(horizontal = MovieSpace.Medium, vertical = MovieSpace.Medium),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
        ) {
            MovieText(
                text = "Secure Your Account",
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
                label = "Username",
                placeholder = "Choose a username",
                leading = { MovieIcon(icon = MovieIcons.Person) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            )
            MovieTextInput(
                value = passwordTf,
                onValueChange = {
                    passwordTf = it
                    onPasswordChange(it.text)
                },
                label = "Create Password",
                placeholder = "••••••••",
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
                label = "Confirm Password",
                placeholder = "••••••••",
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
                        onToggle = onToggleConfirmPasswordVisible,
                    )
                },
            )

            PasswordRequirementsCard(
                rules = rules,
                modifier = Modifier.fillMaxWidth(),
            )

            data.formErrorMessage?.let { msg ->
                MovieText(
                    text = msg,
                    variant = MovieTextVariant.TextSmall(),
                    contentColor = MovieTextColor.Brand,
                )
            }

            MovieButton(
                text = "Complete Registration >",
                onClick = onCompleteRegistration,
                variant = MovieButtonVariant.Primary,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Spacer(Modifier.height(MovieSpace.Large))
        MovieText(
            text = "By completing registration, you agree to our Terms of Service and Privacy Policy.",
            variant = MovieTextVariant.TextSmall(),
            contentColor = MovieTextColor.Medium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MovieSpace.Small),
        )
    }
}

@Composable
private fun PasswordVisibilityToggle(visible: Boolean, onToggle: () -> Unit) {
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
            contentDescription = if (visible) "Hide password" else "Show password",
            modifier = Modifier.padding(MovieSpace.Small),
        )
    }
}

@Composable
private fun PasswordRequirementsCard(
    rules: MoviePasswordRulesState,
    modifier: Modifier = Modifier,
) {
    val semantic = MovieTheme.colors
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(MovieCornerRadius.Small))
            .background(semantic.fillNeutral)
            .padding(MovieSpace.Medium),
        verticalArrangement = Arrangement.spacedBy(MovieSpace.Small),
    ) {
        RuleRow("Minimum 8 characters", rules.hasMinLength)
        RuleRow("At least one number", rules.hasDigit)
        RuleRow("At least one letter", rules.hasLetter)
        RuleRow("One special character", rules.hasSpecialChar)
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
