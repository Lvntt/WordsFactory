package dev.lantt.wordsfactory.auth.domain.usecase

import dev.lantt.wordsfactory.core.domain.model.UserRegisterDto
import dev.lantt.wordsfactory.core.domain.repository.AuthRepository

class RegisterUserUseCase(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(user: UserRegisterDto)
        = authRepository.register(user)

}