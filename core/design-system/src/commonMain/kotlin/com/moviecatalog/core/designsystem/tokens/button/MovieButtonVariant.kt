package com.moviecatalog.core.designsystem.tokens.button


enum class MovieButtonVariant {
    Primary,
    Neutral,
    Destructive,
    Ghost,
    ;

    fun defaultLoadingLabel(): String =
        when (this) {
            Primary, Neutral -> "Loading"
            Destructive -> "Processing"
            Ghost -> "Standby"
        }
}
