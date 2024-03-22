package dev.lantt.wordsfactory.auth.domain.model

data class UserRegisterDto(
    val name: String,
    val email: String,
    val password: String
)
