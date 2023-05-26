package likco.likfit.services

import likco.likfit.modals.User
import likco.likfit.services.firebase.FirebaseAuth

object AuthService {
    var user: User?
        get() = FirebaseAuth.user
        private set(value) {
            FirebaseAuth.user = value
        }

    fun login(
        email: String,
        password: String,
        error: () -> Unit = {},
        success: () -> Unit
    ) {
        if (email == "" || password == "") return error()
        FirebaseAuth.EmailAndPassword.login(email, password, error, success)
    }

    fun signup(
        email: String,
        password: String,
        passwordConfirmation: String,
        error: () -> Unit = {},
        success: () -> Unit
    ) {
        if (password != passwordConfirmation) return error()
        if (email == "" || password == "") return error()

        FirebaseAuth.EmailAndPassword.signup(email, password, error, success)
    }

    fun logout(
        error: () -> Unit = {},
        success: () -> Unit
    ) {
        FirebaseAuth.EmailAndPassword.logout(error, success)
    }
}