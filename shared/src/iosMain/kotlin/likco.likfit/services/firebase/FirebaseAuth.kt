package likco.likfit.services.firebase

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRAuthErrorUserInfoNameKey
import cocoapods.FirebaseCore.FIRApp
import likco.likfit.modals.User
import likco.likfit.modals.ui.Error
import likco.likfit.modals.ui.ErrorHandlerFun
import platform.Foundation.NSError


actual object FirebaseAuth {
    private val auth: FIRAuth

    actual var user: User? = null

    actual object EmailAndPassword {
        actual fun login(
            email: String,
            password: String,
            error: ErrorHandlerFun,
            success: () -> Unit
        ) {
            auth.signInWithEmail(email = email, password = password) { result, err ->
                result ?: return@signInWithEmail error(err.error())
                user = User(result.user.email ?: "-?-")
                success()
            }
        }

        actual fun signup(
            email: String,
            password: String,
            error: ErrorHandlerFun,
            success: () -> Unit
        ) {
            auth.createUserWithEmail(email = email, password = password) { result, err ->
                result ?: return@createUserWithEmail error(err.error())
                user = User(result.user.email ?: "-?-")
                success()
            }
        }

        actual fun logout(
            error: ErrorHandlerFun,
            success: () -> Unit
        ) {
            auth.signOut(null)
            user = null
            success()
        }
    }

    init {
        FIRApp.configure()
        this.auth = FIRAuth.auth()
        this.user =
            if (this.auth.currentUser == null) null else User(this.auth.currentUser?.email ?: "-?-")
    }

    private fun NSError?.error() =
        Error(code = this?.userInfo?.get(FIRAuthErrorUserInfoNameKey)?.toString() ?: "")
}

