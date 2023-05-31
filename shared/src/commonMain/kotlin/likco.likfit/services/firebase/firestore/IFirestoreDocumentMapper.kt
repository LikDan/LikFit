package likco.likfit.services.firebase.firestore

interface IFirestoreDocumentMapper <T> {
    fun from(document: RawFirestoreDocument): T
    fun to(document: T): RawFirestoreDocument

    fun mapper(): FirestoreDocumentMapper<T> = FirestoreDocumentMapper(this::from, this::to)
}