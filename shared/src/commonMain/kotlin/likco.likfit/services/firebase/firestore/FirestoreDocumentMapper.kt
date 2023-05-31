package likco.likfit.services.firebase.firestore

typealias FirestoreOperationsFromRawMapperFun<T> = (RawFirestoreDocument) -> T
typealias FirestoreOperationsToRawMapperFun<T> = (T) -> RawFirestoreDocument

data class FirestoreDocumentMapper<T>(
    val from: FirestoreOperationsFromRawMapperFun<T>,
    val to: FirestoreOperationsToRawMapperFun<T>,
)