package likco.likfit.services.firebase.firestore

import likco.likfit.models.ui.OnError
import likco.likfit.services.firebase.auth.FirebaseAuth

object Firestore {
    private const val privateCollection = "users"
    private fun getDocumentPath(collection: String, private: Boolean = false): String =
        if (private) "${privateCollection}/${FirebaseAuth.user?.id}/$collection" else collection

    open class DocumentCRUD<T>(
        collection: String,
        protected val mapper: FirestoreDocumentMapper<T>,
        protected val onError: OnError = {},
        private: Boolean = false,
        document: String = "self"
    ) {
        private val documentPath = "${getDocumentPath(collection, private)}/${document}"

        open fun get(error: OnError = onError, success: (T) -> Unit) =
            FirestoreOperations.get(documentPath, mapper.from, error, success)

        open fun create(document: T, error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.create(documentPath, document, mapper.to, error, success)

        open fun update(document: T, error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.create(documentPath, document, mapper.to, error, success)

        open fun delete(error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.delete(documentPath, error, success)
    }

    open class CRUD<T : Any>(
        collection: String,
        protected val mapper: FirestoreDocumentMapper<T>,
        protected val onError: OnError = {},
        private: Boolean = false
    ) {
        private val documentPath = getDocumentPath(collection, private)

        open fun get(id: String, error: OnError = onError, success: (T) -> Unit) =
            FirestoreOperations.get("$documentPath/$id", mapper.from, error, success)

        open fun create(id: String, document: T, error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.create("$documentPath/$id", document, mapper.to, error, success)

        open fun update(id: String, document: T, error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.update("$documentPath/$id", document, mapper.to, error, success)

        open fun delete(id: String, error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.delete("$documentPath/$id", error, success)
    }
}