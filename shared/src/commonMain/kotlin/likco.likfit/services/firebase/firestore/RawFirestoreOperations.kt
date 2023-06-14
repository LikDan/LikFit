package likco.likfit.services.firebase.firestore

import likco.likfit.models.ui.OnError

typealias RawFirestoreDocument = Map<String, Any?>
typealias WhereFireStoreCauseEntry = Triple<String, FirestoreOperationsType, String>
typealias WhereFireStoreCause = List<WhereFireStoreCauseEntry>

expect object RawFirestoreOperations {
    fun index(
        collectionPath: String,
        where: WhereFireStoreCause = emptyList(),
        error: OnError = {},
        success: (List<RawFirestoreDocument>) -> Unit
    )

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