package likco.likfit.services.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import likco.likfit.models.ui.SnackBar
import likco.likfit.models.ui.SnackBarType

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
    fun view(modifier: Modifier = Modifier) {
        this@SnackBarHandler.state = remember { SnackbarHostState() }
        this@SnackBarHandler.scope = rememberCoroutineScope()

        SnackbarHost(hostState = this@SnackBarHandler.state!!, modifier) {
            val type = current?.copy()?.type ?: return@SnackbarHost
            Snackbar(
                snackbarData = it,
                contentColor = type.textColor,
                backgroundColor = type.backgroundColor,
            )
        }
    }
}