package likco.likfit.services

import likco.likfit.models.Profile
import likco.likfit.models.ui.Error
import likco.likfit.models.ui.OnError
import likco.likfit.services.firebase.firestore.Firestore
import likco.likfit.services.ui.ErrorHandler

object ProfileService :
    Firestore.DocumentCRUD<Profile>("profile", Profile.mapper(), ErrorHandler::handle, true) {

    private val nullProfileError = Error(code = "NO_PROFILE")

    override fun get(error: OnError, success: (Profile) -> Unit) {
        super.get(error) parentGet@{
            AuthService.user ?: return@parentGet error(nullProfileError)

            AuthService.user!!.profile = if (it.height == 0f) null else it
            AuthService.user!!.profile ?: return@parentGet error(nullProfileError)

            success(AuthService.user!!.profile!!)
        }
    }
}