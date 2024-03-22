package dev.lantt.wordsfactory.auth.domain.repository

import dev.lantt.wordsfactory.auth.domain.model.UserLoginDto
import dev.lantt.wordsfactory.auth.domain.model.UserRegisterDto

interface AuthRepository {

    suspend fun register(user: UserRegisterDto)

    suspend fun login(user: UserLoginDto)

}