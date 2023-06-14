package likco.likfit.services.firebase.firestore

import likco.likfit.models.ui.OnError
import likco.likfit.services.firebase.auth.FirebaseAuth

object Firestore {
    private const val privateCollection = "users"
    private fun getCollectionPath(collection: String, private: Boolean = false): String =
        if (private) "${privateCollection}/${FirebaseAuth.user?.id}/$collection" else collection

    open class DocumentCRUD<T>(
        protected val collection: String,
        protected val mapper: FirestoreDocumentMapper<T>,
        protected val onError: OnError = {},
        protected val private: Boolean = false,
        protected val document: String = "self"
    ) {
        private val collectionPath get() = "${getCollectionPath(collection, private)}/${document}"

        open fun get(error: OnError = onError, success: (T) -> Unit) =
            FirestoreOperations.get(collectionPath, mapper.from, error, success)

        open fun create(document: T, error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.create(collectionPath, document, mapper.to, error, success)

        open fun update(document: T, error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.create(collectionPath, document, mapper.to, error, success)

        open fun delete(error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.delete(collectionPath, error, success)
    }

    open class CRUD<T : Any>(
        collection: String,
        protected val mapper: FirestoreDocumentMapper<T>,
        protected val onError: OnError = {},
        private: Boolean = false
    ) {
        private val collectionPath = getCollectionPath(collection, private)

        open fun index(
            where: WhereFireStoreCause = emptyList(),
            error: OnError = onError,
            success: (List<T>) -> Unit
        ) =
            FirestoreOperations.index(collectionPath, where, mapper.from, error, success)

        open fun get(id: String, error: OnError = onError, success: (T) -> Unit) =
            FirestoreOperations.get("$collectionPath/$id", mapper.from, error, success)

        open fun create(id: String, document: T, error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.create("$collectionPath/$id", document, mapper.to, error, success)

        open fun update(id: String, document: T, error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.update("$collectionPath/$id", document, mapper.to, error, success)

        open fun delete(id: String, error: OnError = onError, success: () -> Unit) =
            FirestoreOperations.delete("$collectionPath/$id", error, success)
    }
}