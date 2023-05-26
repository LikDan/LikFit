package likco.likfit.services.firebase

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseCore.FIRApp
import likco.likfit.modals.User


actual object FirebaseAuth {
    private val auth: FIRAuth

    actual var user: User? = null

    actual object EmailAndPassword {
        actual fun login(email: String, password: String, error: () -> Unit, success: () -> Unit) {
            auth.signInWithEmail(email = email, password = password) { result, err ->
                result ?: return@signInWithEmail error()
                user = User(result.user.email ?: "-?-")
                success()
            }
        }

        actual fun signup(email: String, password: String, error: () -> Unit, success: () -> Unit) {
            auth.createUserWithEmail(email = email, password = password) { result, _ ->
                result ?: return@createUserWithEmail error()
                user = User(result.user.email ?: "-?-")
                success()
            }
        }

        actual fun logout(error: () -> Unit, success: () -> Unit) {
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
}

