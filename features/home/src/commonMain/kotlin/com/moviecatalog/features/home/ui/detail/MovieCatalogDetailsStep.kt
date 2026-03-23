package com.moviecatalog.features.home.ui.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.theme.MovieTheme
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.core.navigator.step.Step
import com.moviecatalog.core.navigator.step.StepKey
import com.moviecatalog.core.navigator.step.StepNavigationOptions
import com.moviecatalog.features.home.data.MuseumObject
import com.moviecatalog.features.home.generated.resources.Res
import com.moviecatalog.features.home.generated.resources.back
import com.moviecatalog.features.home.generated.resources.details_screen_title
import com.moviecatalog.features.home.generated.resources.label_artist
import com.moviecatalog.features.home.generated.resources.label_credits
import com.moviecatalog.features.home.generated.resources.label_date
import com.moviecatalog.features.home.generated.resources.label_department
import com.moviecatalog.features.home.generated.resources.label_dimensions
import com.moviecatalog.features.home.generated.resources.label_medium
import com.moviecatalog.features.home.generated.resources.label_repository
import com.moviecatalog.features.home.generated.resources.label_title
import com.moviecatalog.features.home.ui.EmptyScreenContent
import com.moviecatalog.features.home.ui.detail.componentes.MovieDetailConfigs
import com.moviecatalog.features.home.ui.detail.componentes.detailsRow.MovieDetailMetadataRow
import com.moviecatalog.features.home.ui.detail.componentes.section.MovieDetailHeroSection
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

internal data class MovieCatalogDetailsStep(val movieId: Int) : Step() {

    override val key: StepKey = "MovieCatalogDetailsStep-$movieId"

    override val navigationOptions: StepNavigationOptions
        @Composable
        get() {
            val barTitle = stringResource(Res.string.details_screen_title)
            val backLabel = stringResource(Res.string.back)
            return remember(movieId, barTitle, backLabel) {
                StepNavigationOptions(
                    title = barTitle,
                    showNavigationAction = true,
                    navigationContentDescription = backLabel,
                )
            }
        }

    @Composable
    override fun Content() {
        val viewModel = koinViewModel<MovieCatalogDetailsViewModel>()
        val obj by viewModel.getObject(movieId).collectAsStateWithLifecycle(initialValue = null)
        AnimatedContent(obj != null) { objectAvailable ->
            if (objectAvailable) {
                MovieDetailsContent(obj!!)
            } else {
                EmptyScreenContent(Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
private fun MovieDetailsContent(obj: MuseumObject, modifier: Modifier = Modifier) {
    val colors = MovieTheme.colors
    val heroUrl = obj.primaryImage.ifBlank { obj.primaryImageSmall }

    Column(
        modifier
            .fillMaxWidth()
            .background(colors.backgroundBody)
            .verticalScroll(rememberScrollState()),
    ) {
        MovieDetailHeroSection(
            imageUrl = heroUrl,
            contentDescription = obj.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MovieDetailConfigs.ScreenHorizontalPadding),
        )
        Spacer(Modifier.height(MovieDetailConfigs.HeroToTitleGap))
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = MovieDetailConfigs.ScreenHorizontalPadding),
        ) {
            MovieText(
                text = obj.title,
                variant = MovieTextVariant.HeadingLarge(FontWeight.Bold),
                contentColor = MovieTextColor.High,
            )
            Spacer(Modifier.height(MovieDetailConfigs.TitleAccentGap))
            Box(
                Modifier
                    .width(MovieDetailConfigs.AccentLineWidth)
                    .height(MovieDetailConfigs.AccentLineHeight)
                    .background(colors.contentBrand),
            )
            Spacer(Modifier.height(MovieDetailConfigs.MetadataSectionTopGap))
            SelectionContainer {
                Column(
                    verticalArrangement = Arrangement.spacedBy(MovieDetailConfigs.MetadataBlockSpacing),
                ) {
                    MovieDetailMetadataRow(
                        label = stringResource(Res.string.label_title),
                        value = obj.title,
                    )
                    MovieDetailMetadataRow(
                        label = stringResource(Res.string.label_artist),
                        value = obj.artistDisplayName,
                    )
                    MovieDetailMetadataRow(
                        label = stringResource(Res.string.label_date),
                        value = obj.objectDate,
                    )
                    MovieDetailMetadataRow(
                        label = stringResource(Res.string.label_dimensions),
                        value = obj.dimensions,
                    )
                    MovieDetailMetadataRow(
                        label = stringResource(Res.string.label_medium),
                        value = obj.medium,
                    )
                    MovieDetailMetadataRow(
                        label = stringResource(Res.string.label_department),
                        value = obj.department,
                    )
                    MovieDetailMetadataRow(
                        label = stringResource(Res.string.label_repository),
                        value = obj.repository,
                    )
                    MovieDetailMetadataRow(
                        label = stringResource(Res.string.label_credits),
                        value = obj.creditLine,
                    )
                }
            }
        }
        Spacer(Modifier.height(MovieDetailConfigs.ScreenHorizontalPadding))
    }
}
