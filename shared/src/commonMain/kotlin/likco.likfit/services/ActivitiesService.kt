package likco.likfit.services

import likco.likfit.models.Activity
import likco.likfit.models.ui.OnError
import likco.likfit.services.firebase.firestore.Firestore
import likco.likfit.services.firebase.firestore.WhereFireStoreCause
import moe.tlaster.precompose.navigation.BackStackEntry
import moe.tlaster.precompose.navigation.path

object ActivitiesService : Firestore.CRUD<Activity>("activities", Activity.mapper()) {
    var activities: List<Activity> = emptyList()
    override fun index(
        where: WhereFireStoreCause,
        error: OnError,
        success: (List<Activity>) -> Unit
    ) {
        super.index(where, error) {
            activities = it
            success(it)
        }
    }

    fun getByName(name: String?): Activity? {
        name ?: return null
        return this.activities.find { it.name == name }
    }

    fun BackStackEntry.activity(): Activity {
        return getByName(this.path<String>("activity"))!!
    }
}