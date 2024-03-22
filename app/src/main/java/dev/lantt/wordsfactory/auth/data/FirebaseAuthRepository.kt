package dev.lantt.wordsfactory.auth.data

import com.google.firebase.auth.FirebaseAuth
import dev.lantt.wordsfactory.auth.domain.model.UserLoginDto
import dev.lantt.wordsfactory.auth.domain.model.UserRegisterDto
import dev.lantt.wordsfactory.auth.domain.repository.AuthRepository

class FirebaseAuthRepository(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun register(user: UserRegisterDto) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    override suspend fun login(user: UserLoginDto) {
        TODO("Not yet implemented")
    }

}