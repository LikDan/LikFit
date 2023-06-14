package likco.likfit.services

expect object DataStoreService {
    fun save(key: String, value: String)
    fun load(key: String): String?
}