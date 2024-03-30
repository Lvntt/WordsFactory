package dev.lantt.wordsfactory.core.domain.repository

import dev.lantt.wordsfactory.core.domain.model.UserLoginDto
import dev.lantt.wordsfactory.core.domain.model.UserRegisterDto

interface AuthRepository {

    suspend fun register(user: UserRegisterDto)

    suspend fun login(user: UserLoginDto)

}