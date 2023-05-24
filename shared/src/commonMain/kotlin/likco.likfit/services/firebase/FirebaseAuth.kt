package likco.likfit.services.firebase

import likco.likfit.modals.User

expect object FirebaseAuth {
    var user: User?

    object EmailAndPassword {
        fun login(email: String, password: String, error: () -> Unit = {}, success: () -> Unit)
        fun signup(email: String, password: String, error: () -> Unit = {}, success: () -> Unit)
    }
}