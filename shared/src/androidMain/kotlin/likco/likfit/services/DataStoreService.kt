package likco.likfit.services

import android.content.SharedPreferences

actual object DataStoreService {
    lateinit var preferences: SharedPreferences

    actual fun save(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    actual fun load(key: String): String? {
        return preferences.getString(key, null)
    }
}