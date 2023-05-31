package likco.likfit.services.firebase.firestore

import likco.likfit.models.ui.OnError

typealias RawFirestoreDocument = Map<String, Any?>

expect object RawFirestoreOperations {
    fun get(
        documentPath: String,
        error: OnError = {},
        success: (RawFirestoreDocument) -> Unit
    )

    fun create(
        documentPath: String,
        document: RawFirestoreDocument,
        error: OnError = {},
        success: () -> Unit
    )

    fun update(
        documentPath: String,
        document: RawFirestoreDocument,
        error: OnError = {},
        success: () -> Unit
    )

    fun delete(
        documentPath: String,
        error: OnError = {},
        success: () -> Unit
    )
}