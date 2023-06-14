package likco.likfit.services

import likco.likfit.models.UserActivity
import likco.likfit.services.firebase.firestore.Firestore

object UserActivitiesService :
    Firestore.CRUD<UserActivity>("activities", UserActivity.mapper(), private = true) {
}