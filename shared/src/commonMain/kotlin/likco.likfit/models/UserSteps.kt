package likco.likfit.models

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import likco.likfit.services.firebase.firestore.IFirestoreDocumentMapper
import likco.likfit.services.firebase.firestore.RawFirestoreDocument
import likco.likfit.utils.now
import likco.likfit.utils.parseOrNull

data class UserSteps(
    val time: LocalDate,
    val steps: Int,
) {
    companion object : IFirestoreDocumentMapper<UserSteps> {
        override fun from(document: RawFirestoreDocument): UserSteps {
            return UserSteps(
                LocalDate.parseOrNull(document["time"].toString()) ?: LocalDate.now(),
                document["steps"].toString().toIntOrNull() ?: 0,
            )
        }

        override fun to(document: UserSteps): RawFirestoreDocument {
            return mapOf(
                "time" to document.time.toString(),
                "steps" to document.steps,
            )
        }
    }

}