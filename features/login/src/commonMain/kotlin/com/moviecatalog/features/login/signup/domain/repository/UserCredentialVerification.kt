package com.moviecatalog.features.login.signup.domain.repository

internal enum class UserCredentialVerification {
    Match,
    UnknownUser,
    WrongPassword,
}
