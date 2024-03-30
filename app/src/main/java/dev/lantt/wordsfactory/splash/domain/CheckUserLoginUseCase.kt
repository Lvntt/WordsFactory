package dev.lantt.wordsfactory.splash.domain

import dev.lantt.wordsfactory.core.domain.repository.AuthRepository

class CheckUserLoginUseCase(
    private val authRepository: AuthRepository
) {

    operator fun invoke(): Boolean = authRepository.isUserLoggedIn()

}