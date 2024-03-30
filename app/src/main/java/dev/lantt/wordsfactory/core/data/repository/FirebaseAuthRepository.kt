package dev.lantt.wordsfactory.core.data.repository

import com.google.firebase.auth.FirebaseAuth
import dev.lantt.wordsfactory.core.domain.model.UserLoginDto
import dev.lantt.wordsfactory.core.domain.model.UserRegisterDto
import dev.lantt.wordsfactory.core.domain.repository.AuthRepository

class FirebaseAuthRepository(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun register(user: UserRegisterDto) {
        firebaseAuth.createUserWithEmailAndPassword(user.email, user.password)
    }

    override suspend fun login(user: UserLoginDto) {
        TODO("Not yet implemented")
    }

    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

}