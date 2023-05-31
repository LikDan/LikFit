package likco.likfit.models

import likco.likfit.services.firebase.firestore.IFirestoreDocumentMapper
import likco.likfit.services.firebase.firestore.RawFirestoreDocument

data class Profile(val name: String) {
    companion object: IFirestoreDocumentMapper<Profile> {
        override fun from(document: RawFirestoreDocument): Profile {
            return Profile(document["name"]?.toString() ?: "-?-")
        }

        override fun to(document: Profile): RawFirestoreDocument {
            return mapOf("name" to document.name)
        }
    }
}
