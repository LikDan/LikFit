package likco.likfit.models

import kotlinx.datetime.LocalDateTime
import likco.likfit.services.firebase.firestore.IFirestoreDocumentMapper
import likco.likfit.services.firebase.firestore.RawFirestoreDocument
import likco.likfit.utils.now
import likco.likfit.utils.parseOrNull

data class UserActivity(
    val name: String,
    val totalSeconds: Long,
    val timeStart: LocalDateTime,
    val totalCalories: Float,
    val totalSteps: Int,
) {
    companion object : IFirestoreDocumentMapper<UserActivity> {
        override fun from(document: RawFirestoreDocument): UserActivity {
            return UserActivity(
                document["name"] as? String ?: "",
                document["totalSeconds"].toString().toLong(),
                LocalDateTime.parseOrNull(document["timeStart"].toString()) ?: LocalDateTime.now(),
                document["totalCalories"].toString().toFloatOrNull() ?: 0f,
                document["totalSteps"].toString().toIntOrNull() ?: 0,
            )
        }

        override fun to(document: UserActivity): RawFirestoreDocument {
            return mapOf(
                "name" to document.name,
                "totalSeconds" to document.totalSeconds,
                "timeStart" to document.timeStart.toString(),
                "totalCalories" to document.totalCalories,
                "totalSteps" to document.totalSteps,
            )
        }
    }

}