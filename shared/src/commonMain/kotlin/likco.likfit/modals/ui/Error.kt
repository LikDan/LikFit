package likco.likfit.modals.ui

import likco.likfit.services.ui.ErrorHandler

data class Error(val message: String? = null, val code: String? = null) {

    val text: String
        get() =
            ErrorHandler.getMessageByCode(code)
                ?: message
                ?: ErrorHandler.getMessageByCode(NOT_FOUND_MESSAGE_CODE)
                ?: ""

    companion object {
        private const val NOT_FOUND_MESSAGE_CODE = "ERR_MESSAGE_FOR_CODE_NOT_FOUND"
    }
}

typealias ErrorHandlerFun = (Error) -> Unit
