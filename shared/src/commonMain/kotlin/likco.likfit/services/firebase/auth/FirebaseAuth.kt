package likco.likfit.services.firebase.auth

import likco.likfit.models.User
import likco.likfit.models.ui.OnError

expect object FirebaseAuth {
    var user: User?

    object EmailAndPassword {
        fun login(
            email: String,
            password: String,
            error: OnError = {},
            success: (User) -> Unit
        )

        fun signup(
            email: String,
            password: String,
            error: OnError = {},
            success: (User) -> Unit
        )

        fun logout(
            error: OnError = {},
            success: () -> Unit
        )
    }
}