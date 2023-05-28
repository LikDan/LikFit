package likco.likfit.i18n

enum class Strings {
    ERR_NO_IMPLEMENTED,
    ERROR_USER_NOT_FOUND,
    ERROR_INVALID_EMAIL,
    ERROR_WRONG_PASSWORD,
    ERROR_FIELD_REQUIRED,
    ERROR_PASSWORDS_MISMATCH,

    LOGIN,
    SIGNUP,
    LOGOUT;

    override fun toString(): String = locale.getOrCode(this)

    companion object {
        var locale: Languages = Languages.EN_US

        fun contains(value: String) = runCatching {
            Strings.valueOf(value)
            true
        }.getOrElse { false }

        fun getOrNull(value: String): String? = runCatching {
            Strings.valueOf(value).toString()
        }.getOrNull()
    }
}