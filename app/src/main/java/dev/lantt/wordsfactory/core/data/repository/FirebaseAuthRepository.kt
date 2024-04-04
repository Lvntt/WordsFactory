package dev.lantt.wordsfactory.core.data.repository

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import dev.lantt.wordsfactory.core.domain.model.UserLoginDto
import dev.lantt.wordsfactory.core.domain.model.UserRegisterDto
import dev.lantt.wordsfactory.core.domain.model.exception.InvalidCredentialsException
import dev.lantt.wordsfactory.core.domain.model.exception.UserCollisionException
import dev.lantt.wordsfactory.core.domain.model.exception.WeakPasswordException
import dev.lantt.wordsfactory.core.domain.repository.AuthRepository
import kotlinx.coroutines.tasks.await

class FirebaseAuthRepository(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {

    override suspend fun register(user: UserRegisterDto) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(user.email, user.password).await()
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            throw InvalidCredentialsException()
        } catch (e: FirebaseAuthUserCollisionException) {
            throw UserCollisionException()
        } catch (e: FirebaseAuthWeakPasswordException) {
            throw WeakPasswordException()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun login(user: UserLoginDto) {
        TODO("Not yet implemented")
    }

    override fun isUserLoggedIn(): Boolean {
        Log.d("FirebaseAuthRepository", "${firebaseAuth.currentUser != null}")
        return firebaseAuth.currentUser != null
    }

}