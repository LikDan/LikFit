package likco.likfit.services.firebase.firestore

import cocoapods.FirebaseFirestore.FIRDocumentSnapshot
import cocoapods.FirebaseFirestore.FIRFirestore
import likco.likfit.models.ui.Error
import likco.likfit.models.ui.OnError
import platform.Foundation.NSError

actual object RawFirestoreOperations {
    private val db = FIRFirestore.firestore()

    actual fun index(
        collectionPath: String,
        error: OnError,
        success: (List<RawFirestoreDocument>) -> Unit
    ) {
        db.collectionWithPath(collectionPath).getDocumentsWithCompletion { result, err ->
            result ?: return@getDocumentsWithCompletion error(err.error())
            success(result.documents.map { it as? FIRDocumentSnapshot }.map {
                it?.data()?.mapKeys { k -> k.key.toString() } ?: emptyMap()
            })
        }
    }

    actual fun get(
        documentPath: String,
        error: OnError,
        success: (RawFirestoreDocument) -> Unit
    ) {
        db.documentWithPath(documentPath).getDocumentWithCompletion { result, err ->
            result ?: return@getDocumentWithCompletion error(err.error())
            success(result.data()?.mapKeys { it.key.toString() } ?: emptyMap())
        }
    }

    actual fun create(
        documentPath: String,
        document: RawFirestoreDocument,
        error: OnError,
        success: () -> Unit
    ) {
        db.documentWithPath(documentPath).setData(document.mapKeys { it.key }) { err ->
            err ?: return@setData success()
            error(err.error())
        }
    }

    actual fun update(
        documentPath: String,
        document: RawFirestoreDocument,
        error: OnError,
        success: () -> Unit
    ) {
        db.documentWithPath(documentPath).updateData(document.mapKeys { it.key }) { err ->
            err ?: return@updateData success()
            error(err.error())
        }
    }

    actual fun delete(
        documentPath: String,
        error: OnError,
        success: () -> Unit
    ) {
        db.documentWithPath(documentPath).deleteDocumentWithCompletion { err ->
            err ?: return@deleteDocumentWithCompletion success()
            error(err.error())
        }
    }

    private fun NSError?.error() = Error(code = errors[this?.code?.toInt() ?: 2])

    //firestore assure that 0 code will never happens on error
    private val errors = mapOf(
        0 to "ERROR_OK",
        1 to "ERROR_CANCELLED",
        2 to "ERROR_UNKNOWN",
        3 to "ERROR_INVALID_ARGUMENT",
        4 to "ERROR_DEADLINE_EXCEEDED",
        5 to "ERROR_NOT_FOUND",
        6 to "ERROR_ALREADY_EXISTS",
        7 to "ERROR_PERMISSION_DENIED",
        8 to "ERROR_RESOURCE_EXHAUSTED",
        9 to "ERROR_FAILED_PRECONDITION",
        10 to "ERROR_ABORTED",
        11 to "ERROR_OUT_OF_RANGE",
        12 to "ERROR_UNIMPLEMENTED",
        13 to "ERROR_INTERNAL",
        14 to "ERROR_UNAVAILABLE",
        15 to "ERROR_DATA_LOSS",
        16 to "ERROR_UNAUTHENTICATED",
    )
}