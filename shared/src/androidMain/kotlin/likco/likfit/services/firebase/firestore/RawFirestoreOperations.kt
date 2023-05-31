package likco.likfit.services.firebase.firestore

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import likco.likfit.models.ui.Error
import likco.likfit.models.ui.OnError

actual object RawFirestoreOperations {
    private val db: FirebaseFirestore = Firebase.firestore

    actual fun get(
        documentPath: String,
        error: OnError,
        success: (RawFirestoreDocument) -> Unit
    ) {
        db.document(documentPath).get()
            .addOnSuccessListener { d ->
                d.data ?: return@addOnSuccessListener success(emptyMap())
                success(d.data!!.mapKeys { it.key.toString() })
            }
            .addOnFailureListener { error(it.error()) }
    }

    actual fun create(
        documentPath: String,
        document: RawFirestoreDocument,
        error: OnError,
        success: () -> Unit
    ) {
        db.document(documentPath).set(document)
            .addOnSuccessListener { success() }
            .addOnFailureListener { error(it.error()) }
    }

    actual fun update(
        documentPath: String,
        document: RawFirestoreDocument,
        error: OnError,
        success: () -> Unit
    ) {
        db.document(documentPath).update(document)
            .addOnSuccessListener { success() }
            .addOnFailureListener { error(it.error()) }
    }

    actual fun delete(
        documentPath: String,
        error: OnError,
        success: () -> Unit
    ) {
        db.document(documentPath).delete()
            .addOnSuccessListener { success() }
            .addOnFailureListener { error(it.error()) }
    }

    private fun Exception.error() =
        Error(code = "ERROR_${(this as FirebaseFirestoreException).code.name}")
}
