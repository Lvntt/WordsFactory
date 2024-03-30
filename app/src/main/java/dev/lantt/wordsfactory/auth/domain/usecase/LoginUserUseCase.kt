package dev.lantt.wordsfactory.auth.domain.usecase

import dev.lantt.wordsfactory.core.domain.model.UserLoginDto
import dev.lantt.wordsfactory.core.domain.repository.AuthRepository

class LoginUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(user: UserLoginDto)
        = authRepository.login(user)

}