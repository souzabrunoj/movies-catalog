package com.moviecatalog.features.home.ui.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.size.MovieComponentSize
import com.moviecatalog.core.designsystem.tokens.size.MovieSpace
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.features.home.data.MuseumObject
import com.moviecatalog.features.home.generated.resources.Res
import com.moviecatalog.features.home.generated.resources.back
import com.moviecatalog.features.home.generated.resources.label_artist
import com.moviecatalog.features.home.generated.resources.label_credits
import com.moviecatalog.features.home.generated.resources.label_date
import com.moviecatalog.features.home.generated.resources.label_department
import com.moviecatalog.features.home.generated.resources.label_dimensions
import com.moviecatalog.features.home.generated.resources.label_medium
import com.moviecatalog.features.home.generated.resources.label_repository
import com.moviecatalog.features.home.generated.resources.label_title
import com.moviecatalog.features.home.ui.EmptyScreenContent
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
public fun DetailScreen(
    objectId: Int,
    navigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<DetailViewModel>()

    val obj by viewModel.getObject(objectId).collectAsStateWithLifecycle(initialValue = null)
    AnimatedContent(obj != null) { objectAvailable ->
        if (objectAvailable) {
            ObjectDetails(obj!!, onBackClick = navigateBack)
        } else {
            EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun ObjectDetails(
    obj: MuseumObject,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val colors = MovieTheme.colors
    Column(
        modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.systemBars),
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(colors.backgroundSurface)
                .padding(horizontal = MovieSpace.XSmall2, vertical = MovieSpace.XSmall),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(Res.string.back),
                modifier = Modifier
                    .clickable(onClick = onBackClick)
                    .padding(MovieSpace.Small)
                    .size(MovieComponentSize.IconMedium),
                colorFilter = ColorFilter.tint(colors.contentHigh),
            )
        }

        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth(),
        ) {
            AsyncImage(
                model = obj.primaryImageSmall,
                contentDescription = obj.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.backgroundSurface),
            )

            SelectionContainer {
                Column(Modifier.padding(MovieSpace.Small)) {
                    MovieText(
                        text = obj.title,
                        variant = MovieTextVariant.HeadingMedium(),
                        contentColor = MovieTextColor.High,
                    )
                    Spacer(Modifier.height(MovieSpace.XSmall2 + MovieSpace.XSmall3))
                    LabeledInfo(stringResource(Res.string.label_title), obj.title)
                    LabeledInfo(stringResource(Res.string.label_artist), obj.artistDisplayName)
                    LabeledInfo(stringResource(Res.string.label_date), obj.objectDate)
                    LabeledInfo(stringResource(Res.string.label_dimensions), obj.dimensions)
                    LabeledInfo(stringResource(Res.string.label_medium), obj.medium)
                    LabeledInfo(stringResource(Res.string.label_department), obj.department)
                    LabeledInfo(stringResource(Res.string.label_repository), obj.repository)
                    LabeledInfo(stringResource(Res.string.label_credits), obj.creditLine)
                }
            }
        }
    }
}

@Composable
private fun LabeledInfo(
    label: String,
    data: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(vertical = MovieSpace.XSmall2)) {
        Spacer(Modifier.height(MovieSpace.XSmall2 + MovieSpace.XSmall3))
        MovieText(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$label: ")
                }
                append(data)
            },
            variant = MovieTextVariant.TextMedium(),
            contentColor = MovieTextColor.Medium,
        )
    }
}
