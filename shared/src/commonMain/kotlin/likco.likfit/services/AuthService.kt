package likco.likfit.services

import kotlinx.datetime.LocalDate
import likco.likfit.i18n.Strings
import likco.likfit.models.Gender
import likco.likfit.models.Profile
import likco.likfit.models.User
import likco.likfit.models.ui.Error
import likco.likfit.models.ui.OnError
import likco.likfit.services.firebase.auth.FirebaseAuth
import likco.likfit.services.ui.ErrorHandler

object AuthService {
    var user: User?
        get() = FirebaseAuth.user
        private set(value) {
            FirebaseAuth.user = value
        }


    fun login(
        email: String,
        password: String,
        error: OnError = ErrorHandler::handle,
        success: (User) -> Unit
    ) {
        if (email == "" || password == "") return error(Error(code = "ERROR_FIELD_REQUIRED"))
        FirebaseAuth.EmailAndPassword.login(email, password, error, success)
    }

    fun signup(
        email: String,
        password: String,
        passwordConfirmation: String,
        error: OnError = ErrorHandler::handle,
        success: (User) -> Unit
    ) {
        if (email == "" || password == "") return error(Error(code = "ERROR_FIELD_REQUIRED"))
        if (password != passwordConfirmation) return error(Error(code = "ERROR_PASSWORDS_MISMATCH"))

        FirebaseAuth.EmailAndPassword.signup(email, password, error, success)
    }

    fun logout(
        error: OnError = ErrorHandler::handle,
        success: () -> Unit
    ) {
        FirebaseAuth.EmailAndPassword.logout(error, success)
    }

    fun createProfile(
        height: Float?,
        weight: Float?,
        birthday: LocalDate?,
        gender: Gender?,
        error: OnError = ErrorHandler::handle,
        success: (Profile) -> Unit
    ) {
        if (height == null || weight == null || birthday == null || gender == null)
            return error(Error(code = Strings.ERROR_FIELD_REQUIRED.name))

        val profile = Profile(height, weight, birthday, gender, null, null)
        ProfileService.create(profile, error) { success(profile) }
    }

    fun updateProfile(
        height: Float?,
        weight: Float?,
        birthday: LocalDate?,
        gender: Gender?,
        weightGoal: Float?,
        stepsGoal: Int?,
        error: OnError = ErrorHandler::handle,
        success: (Profile) -> Unit
    ) {
        if (height == null || weight == null || birthday == null || gender == null)
            return error(Error(code = Strings.ERROR_FIELD_REQUIRED.name))

        val profile = Profile(height, weight, birthday, gender, weightGoal, stepsGoal)
        ProfileService.update(profile, error) { success(profile) }
    }
}