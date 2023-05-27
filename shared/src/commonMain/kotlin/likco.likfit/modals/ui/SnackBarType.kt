package likco.likfit.modals.ui

import androidx.compose.ui.graphics.Color

enum class SnackBarType(val textColor: Color, val backgroundColor: Color) {
    ERROR(Color.White, Color.Red),
    INFO(Color.White, Color.Blue),
    SUCCESS(Color.White, Color.Green),
}
