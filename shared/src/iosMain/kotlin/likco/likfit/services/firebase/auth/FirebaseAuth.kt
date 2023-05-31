package likco.likfit.services.firebase.auth

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseAuth.FIRAuthErrorUserInfoNameKey
import cocoapods.FirebaseCore.FIRApp
import likco.likfit.models.User
import likco.likfit.models.ui.Error
import likco.likfit.models.ui.OnError
import platform.Foundation.NSError

actual object FirebaseAuth {
    private val auth: FIRAuth

    actual var user: User? = null

    actual object EmailAndPassword {
        actual fun login(
            email: String,
            password: String,
            error: OnError,
            success: (User) -> Unit
        ) {
            auth.signInWithEmail(email = email, password = password) { result, err ->
                result ?: return@signInWithEmail error(err.error())
                user = User(result.user.uid, result.user.email ?: "-?-")
                success(user!!)
            }
        }

        actual fun signup(
            email: String,
            password: String,
            error: OnError,
            success: (User) -> Unit
        ) {
            auth.createUserWithEmail(email = email, password = password) { result, err ->
                result ?: return@createUserWithEmail error(err.error())
                user = User(result.user.uid, result.user.email ?: "-?-")
                success(user!!)
            }
        }

        actual fun logout(
            error: OnError,
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
        this.user = if (this.auth.currentUser == null) null
        else User(
            this.auth.currentUser?.uid ?: "",
            this.auth.currentUser?.email ?: "-?-"
        )
    }

    private fun NSError?.error() =
        Error(code = this?.userInfo?.get(FIRAuthErrorUserInfoNameKey)?.toString() ?: "")
}