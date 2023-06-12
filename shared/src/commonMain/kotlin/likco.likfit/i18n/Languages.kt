package likco.likfit.i18n

import likco.likfit.i18n.languages.enUS
import likco.likfit.i18n.languages.ruRU

typealias Language = Map<Strings, String>

enum class Languages(val lang: Language, val text: String, val short: String) {
    EN_US(enUS, "English", "En"),
    RU_RU(ruRU, "Русский", "Ру");

    fun getOrNull(code: Strings) = lang[code]

    fun getOrCode(code: Strings) = this.getOrNull(code) ?: code.name
    fun getOrCode(code: String) =
        runCatching { this.getOrCode(Strings.valueOf(code)) }.getOrElse { code }

    operator fun invoke(code: Strings) = getOrCode(code)
    operator fun invoke(code: String) = getOrCode(code)

    override fun toString(): String = this.text
}