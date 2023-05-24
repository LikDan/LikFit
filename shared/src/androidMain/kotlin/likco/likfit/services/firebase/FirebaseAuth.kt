package likco.likfit.services.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import likco.likfit.modals.User

actual object FirebaseAuth {
    private var auth: FirebaseAuth = Firebase.auth
    actual var user: User? = null


    actual object EmailAndPassword {
        actual fun login(email: String, password: String, error: () -> Unit, success: () -> Unit) {
            auth
                .signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener error()

                    user = User(it.result.user?.email ?: "-?-")
                    success()
                }
        }

        actual fun signup(email: String, password: String, error: () -> Unit, success: () -> Unit) {
            auth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener error()

                    user = User(it.result.user?.email ?: "-?-")
                    success()
                }
        }
    }
}

