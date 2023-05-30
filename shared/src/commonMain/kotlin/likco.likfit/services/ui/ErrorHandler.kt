package likco.likfit.services.ui

import likco.likfit.models.ui.Error

object ErrorHandler {
    fun handle(error: Error) {
        SnackBarHandler.error(error.text)
    }
}