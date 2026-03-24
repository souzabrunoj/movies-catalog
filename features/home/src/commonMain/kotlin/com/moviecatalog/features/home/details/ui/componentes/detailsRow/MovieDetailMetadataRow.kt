package com.moviecatalog.features.home.details.ui.componentes.detailsRow

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.moviecatalog.core.designsystem.components.text.MovieText
import com.moviecatalog.core.designsystem.tokens.type.MovieTextColor
import com.moviecatalog.core.designsystem.tokens.type.MovieTextVariant
import com.moviecatalog.features.home.details.ui.componentes.MovieDetailConfigs

@Composable
internal fun MovieDetailMetadataRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        MovieText(
            text = label.uppercase(),
            variant = MovieTextVariant.Overline(FontWeight.Medium),
            contentColor = MovieTextColor.Medium,
        )
        Spacer(Modifier.height(MovieDetailConfigs.MetadataLabelToValueGap))
        MovieText(
            text = value,
            variant = MovieTextVariant.TextMedium(),
            contentColor = MovieTextColor.High,
        )
    }
}
