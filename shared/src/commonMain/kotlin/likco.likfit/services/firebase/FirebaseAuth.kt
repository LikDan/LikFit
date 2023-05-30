package likco.likfit.services.firebase

import likco.likfit.models.User
import likco.likfit.models.ui.ErrorHandlerFun

expect object FirebaseAuth {
    var user: User?

    object EmailAndPassword {
        fun login(
            email: String,
            password: String,
            error: ErrorHandlerFun = {},
            success: (User) -> Unit
        )

        fun signup(
            email: String,
            password: String,
            error: ErrorHandlerFun = {},
            success: (User) -> Unit
        )

        fun logout(
            error: ErrorHandlerFun = {},
            success: () -> Unit
        )
    }
}