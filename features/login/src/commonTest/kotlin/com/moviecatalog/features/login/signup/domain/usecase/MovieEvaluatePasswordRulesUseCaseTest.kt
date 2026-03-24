package com.moviecatalog.features.login.signup.domain.usecase

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class MovieEvaluatePasswordRulesUseCaseTest {

    private val useCase = MovieEvaluatePasswordRulesUseCase()

    @Test
    fun allSatisfied_whenPasswordMeetsRulesAndMatchesConfirm() {
        val rules = useCase("Abcdef1!", "Abcdef1!")
        assertTrue(rules.hasMinLength)
        assertTrue(rules.hasLetter)
        assertTrue(rules.hasDigit)
        assertTrue(rules.hasSpecialChar)
        assertTrue(rules.hasPasswordsMatch)
        assertTrue(rules.allSatisfied)
    }

    @Test
    fun notSatisfied_whenTooShort() {
        val rules = useCase("Ab1!", "Ab1!")
        assertFalse(rules.hasMinLength)
        assertFalse(rules.allSatisfied)
    }

    @Test
    fun notSatisfied_whenNoDigit() {
        val rules = useCase("Abcdefgh!", "Abcdefgh!")
        assertFalse(rules.hasDigit)
        assertFalse(rules.allSatisfied)
    }

    @Test
    fun notSatisfied_whenPasswordsMismatch() {
        val rules = useCase("Abcdef1!", "Abcdef1?")
        assertFalse(rules.hasPasswordsMatch)
        assertFalse(rules.allSatisfied)
    }
}
