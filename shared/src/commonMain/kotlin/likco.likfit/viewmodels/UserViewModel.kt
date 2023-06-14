package likco.likfit.viewmodels

import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDate
import likco.likfit.models.Gender
import likco.likfit.models.Profile
import likco.likfit.models.User
import likco.likfit.models.ui.OnError
import likco.likfit.services.AuthService
import likco.likfit.services.ProfileService
import likco.likfit.services.ui.ErrorHandler
import likco.likfit.utils.viewmodels.StateViewModel

class UserViewModel : StateViewModel<User?>(AuthService.user) {
    fun login(
        email: String,
        password: String,
        error: OnError = ErrorHandler::handle,
        success: () -> Unit = {}
    ) = AuthService.login(email, password, error) {
        mState.update { it }
        success()
    }

    fun signup(
        email: String,
        password: String,
        passwordRepeat: String,
        error: OnError = ErrorHandler::handle,
        success: () -> Unit = {}
    ) = AuthService.signup(email, password, passwordRepeat, error) {
        mState.update { it }
        success()
    }

    fun logout(
        error: OnError = ErrorHandler::handle,
        success: () -> Unit = {}
    ) = AuthService.logout(error) {
        mState.update { null }
        success()
    }

    fun loadProfile(onLoad: (Profile?) -> Unit = {}) =
        ProfileService.get(error = { onLoad(null) }) {
            mState.update { user -> user?.copy(profile = it) }
            onLoad(it)
        }

    fun createProfile(
        height: Float?,
        weight: Float?,
        birthday: LocalDate?,
        gender: Gender?,
        error: OnError = ErrorHandler::handle,
        success: () -> Unit = {}
    ) = AuthService.createProfile(height, weight, birthday, gender, error) { p ->
        mState.update { it?.copy(profile = p) }
        success()
    }

    fun updateProfile(
        height: Float?,
        weight: Float?,
        birthday: LocalDate?,
        gender: Gender?,
        weightGoal: Float?,
        stepsGoal: Int?,
        error: OnError = ErrorHandler::handle,
        success: () -> Unit = {}
    ) = AuthService.updateProfile(
        height,
        weight,
        birthday,
        gender,
        weightGoal,
        stepsGoal,
        error
    ) { p ->
        mState.update { it?.copy(profile = p) }
        success()
    }
}