package likco.likfit.services.firebase

import cocoapods.FirebaseAuth.FIRAuth
import cocoapods.FirebaseCore.FIRApp
import likco.likfit.modals.User


actual object FirebaseAuth {

    actual var user: User? = null

    actual object EmailAndPassword {
        actual fun login(email: String, password: String, error: () -> Unit, success: () -> Unit) {
            FIRAuth.auth().signInWithEmail(email = email, password = password) { result, err ->
                result ?: return@signInWithEmail error()
                user = User(result.user.email ?: "-?-")
                success()
            }
        }

        actual fun signup(email: String, password: String, error: () -> Unit, success: () -> Unit) {
            FIRAuth.auth().createUserWithEmail(email = email, password = password) { result, _ ->
                result ?: return@createUserWithEmail error()
                user = User(result.user.email ?: "-?-")
                success()
            }
        }

        init {
            FIRApp.configure()
        }
    }
}

