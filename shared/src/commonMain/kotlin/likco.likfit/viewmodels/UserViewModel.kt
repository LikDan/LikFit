package likco.likfit.viewmodels

import kotlinx.coroutines.flow.update
import likco.likfit.models.User
import likco.likfit.services.AuthService
import likco.likfit.utils.StateViewModel

class UserViewModel : StateViewModel<User?>(AuthService.user) {
    fun login(email: String, password: String) =
        AuthService.login(email, password) {
            mState.update { AuthService.user }
        }

    fun signup(email: String, password: String, passwordRepeat: String) =
        AuthService.signup(email, password, passwordRepeat) {
            mState.update { AuthService.user }
        }
}