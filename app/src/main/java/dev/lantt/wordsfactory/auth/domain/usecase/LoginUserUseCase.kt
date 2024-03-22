package dev.lantt.wordsfactory.auth.domain.usecase

import dev.lantt.wordsfactory.auth.domain.model.UserLoginDto
import dev.lantt.wordsfactory.auth.domain.repository.AuthRepository

class LoginUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(user: UserLoginDto)
        = authRepository.login(user)

}