package likco.likfit.models.ui

import likco.likfit.i18n.Strings

data class Error(val message: String? = null, val code: String? = null) {
    val text: String
        get() = Strings.getOrNull(this.code ?: "") ?: message ?: code ?: ""
}

typealias ErrorHandlerFun = (Error) -> Unit
