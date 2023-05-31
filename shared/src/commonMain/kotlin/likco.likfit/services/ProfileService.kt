package likco.likfit.services

import likco.likfit.models.Profile
import likco.likfit.services.firebase.firestore.Firestore
import likco.likfit.services.ui.ErrorHandler

object ProfileService :
    Firestore.DocumentCRUD<Profile>("profile", Profile.mapper(), ErrorHandler::handle, true)