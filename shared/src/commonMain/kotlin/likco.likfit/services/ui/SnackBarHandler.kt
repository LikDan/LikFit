package likco.likfit.services.ui

import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import likco.likfit.modals.ui.SnackBar
import likco.likfit.modals.ui.SnackBarType

object SnackBarHandler {
    private var scope: CoroutineScope? = null
    private var state: SnackbarHostState? = null

    var current: SnackBar? = null
        private set

    fun show(snackBar: SnackBar) {
        this.current = snackBar
        this.scope?.launch {
            this@SnackBarHandler.state?.showSnackbar(
                snackBar.message,
                snackBar.icon
            )
        }
    }

    fun hide() {
        this.state?.currentSnackbarData?.dismiss()
    }

    fun error(text: String, icon: String? = null) =
        show(SnackBar(text, SnackBarType.ERROR, icon))

    fun info(text: String, icon: String? = null) =
        show(SnackBar(text, SnackBarType.INFO, icon))

    fun success(text: String, icon: String? = null) =
        show(SnackBar(text, SnackBarType.SUCCESS, icon))

    @Composable
    fun view() {
        this.state = remember { SnackbarHostState() }
        this.scope = rememberCoroutineScope()

        SnackbarHost(hostState = this.state!!, Modifier) {
            val type = current?.copy()?.type ?: return@SnackbarHost
            Snackbar(
                snackbarData = it,
                contentColor = type.textColor,
                backgroundColor = type.backgroundColor
            )
        }
    }
}