package dev.lantt.wordsfactory.core.domain.model

data class UserRegisterDto(
    val name: String,
    val email: String,
    val password: String
)
