package likco.likfit.services

import platform.Foundation.NSUserDefaults

actual object DataStoreService {
    private val preferences = NSUserDefaults.standardUserDefaults()

    actual fun save(key: String, value: String) {
        preferences.setObject(value, key)
    }

    actual fun load(key: String): String? {
        return preferences.objectForKey(key) as? String
    }
}