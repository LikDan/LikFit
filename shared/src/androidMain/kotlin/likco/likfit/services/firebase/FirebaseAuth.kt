package likco.likfit.services.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import likco.likfit.models.User
import likco.likfit.models.ui.Error
import likco.likfit.models.ui.ErrorHandlerFun


actual object FirebaseAuth {
    private var auth: FirebaseAuth = Firebase.auth
    actual var user: User? = null

    actual object EmailAndPassword {
        actual fun login(
            email: String,
            password: String,
            error: ErrorHandlerFun,
            success: (User) -> Unit
        ) {
            auth
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    user = User(it.user?.uid ?: "", it.user?.email ?: "-?-")
                    success(user!!)
                }
                .addOnFailureListener {
                    error(it.error())
                }
        }

        actual fun signup(
            email: String,
            password: String,
            error: ErrorHandlerFun,
            success: (User) -> Unit
        ) {
            auth
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    user = User(it.user?.uid ?: "", it.user?.email ?: "-?-")
                    success(user!!)
                }
                .addOnFailureListener {
                    error(it.error())
                }
        }

        actual fun logout(
            error: ErrorHandlerFun,
            success: () -> Unit
        ) {
            auth.signOut()
            user = null
            success()
        }
    }

    init {
        this.user = if (this.auth.currentUser == null) null
        else User(
            this.auth.currentUser?.uid ?: "",
            this.auth.currentUser?.email ?: "-?-"
        )
    }

    private fun Exception.error() = Error(code = (this as FirebaseAuthException).errorCode)
}

