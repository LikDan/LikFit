package likco.likfit.services.ui

import likco.likfit.modals.ui.Error

object ErrorHandler {
    fun handle(error: Error) {
        SnackBarHandler.error(error.text)
    }
}