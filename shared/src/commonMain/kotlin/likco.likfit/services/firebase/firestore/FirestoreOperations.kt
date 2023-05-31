package likco.likfit.services.firebase.firestore

import likco.likfit.models.ui.OnError

object FirestoreOperations {
    fun <T> get(
        documentPath: String,
        mapper: FirestoreOperationsFromRawMapperFun<T>,
        error: OnError = {},
        success: (T) -> Unit
    ) = RawFirestoreOperations.get(documentPath, error) { success(mapper(it)) }

    fun <T> create(
        documentPath: String,
        document: T,
        mapper: FirestoreOperationsToRawMapperFun<T>,
        error: OnError = {},
        success: () -> Unit
    ) = RawFirestoreOperations.create(documentPath, mapper(document), error, success)

    fun <T> update(
        documentPath: String,
        document: T,
        mapper: FirestoreOperationsToRawMapperFun<T>,
        error: OnError = {},
        success: () -> Unit
    ) = RawFirestoreOperations.update(documentPath, mapper(document), error, success)

    fun delete(
        documentPath: String,
        error: OnError = {},
        success: () -> Unit
    ) = RawFirestoreOperations.delete(documentPath, error, success)
}