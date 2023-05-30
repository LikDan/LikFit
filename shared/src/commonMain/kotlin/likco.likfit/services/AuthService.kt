package likco.likfit.services

import likco.likfit.models.User
import likco.likfit.models.ui.Error
import likco.likfit.models.ui.ErrorHandlerFun
import likco.likfit.services.firebase.FirebaseAuth
import likco.likfit.services.ui.ErrorHandler

object AuthService {
    var user: User?
        get() = FirebaseAuth.user
        private set(value) {
            FirebaseAuth.user = value
        }

    fun login(
        email: String,
        password: String,
        error: ErrorHandlerFun = ErrorHandler::handle,
        success: (User) -> Unit
    ) {
        if (email == "" || password == "") return error(Error(code = "ERROR_FIELD_REQUIRED"))
        FirebaseAuth.EmailAndPassword.login(email, password, error, success)
    }

    fun signup(
        email: String,
        password: String,
        passwordConfirmation: String,
        error: ErrorHandlerFun = ErrorHandler::handle,
        success: (User) -> Unit
    ) {
        if (email == "" || password == "") return error(Error(code = "ERROR_FIELD_REQUIRED"))
        if (password != passwordConfirmation) return error(Error(code = "ERROR_PASSWORDS_MISMATCH"))

        FirebaseAuth.EmailAndPassword.signup(email, password, error, success)
    }

    fun logout(
        error: ErrorHandlerFun = ErrorHandler::handle,
        success: () -> Unit
    ) {
        FirebaseAuth.EmailAndPassword.logout(error, success)
    }
}