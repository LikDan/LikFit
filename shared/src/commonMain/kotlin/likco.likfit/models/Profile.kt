package likco.likfit.models

import kotlinx.datetime.LocalDate
import likco.likfit.services.firebase.firestore.IFirestoreDocumentMapper
import likco.likfit.services.firebase.firestore.RawFirestoreDocument
import likco.likfit.utils.now
import likco.likfit.utils.parseOrNull

data class Profile(
    val height: Float,
    val weight: Float,
    val birthday: LocalDate,
    val gender: Gender
) {
    companion object : IFirestoreDocumentMapper<Profile> {
        override fun from(document: RawFirestoreDocument): Profile {
            return Profile(
                (document["height"] as? Double)?.toFloat() ?: 0f,
                (document["weight"] as? Double)?.toFloat() ?: 0f,
                LocalDate.parseOrNull(document["birthday"] as? String ?: "") ?: LocalDate.now(),
                Gender.valueOf(document["gender"] as? String ?: Gender.NEITHER.name)
            )
        }

        override fun to(document: Profile): RawFirestoreDocument {
            return mapOf(
                "height" to document.height,
                "weight" to document.weight,
                "birthday" to document.birthday.toString(),
                "gender" to document.gender.name,
            )
        }
    }
}
