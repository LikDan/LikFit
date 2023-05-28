package likco.likfit.i18n

import likco.likfit.i18n.languages.enUS
import likco.likfit.i18n.languages.ruRU

typealias Language = Map<Strings, String>

enum class Languages(val lang: Language, val text: String) {
    EN_US(enUS, "English"),
    RU_RU(ruRU, "Русский");

    fun getOrNull(code: Strings) = lang[code]
    fun getOrCode(code: Strings) = this.getOrNull(code) ?: code.name

    override fun toString(): String = this.text
}