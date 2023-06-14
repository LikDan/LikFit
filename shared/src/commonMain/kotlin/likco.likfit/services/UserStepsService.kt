package likco.likfit.services

import kotlinx.datetime.atTime
import likco.likfit.models.UserSteps
import likco.likfit.models.ui.OnError
import likco.likfit.services.firebase.firestore.Firestore
import likco.likfit.services.firebase.firestore.WhereFireStoreCause

object UserStepsService : Firestore.CRUD<UserSteps>("steps", UserSteps.mapper(), private = true) {
}