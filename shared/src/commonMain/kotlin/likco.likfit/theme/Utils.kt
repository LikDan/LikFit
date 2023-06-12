package likco.likfit.theme

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ButtonDefaults.secondary() = buttonColors(
    backgroundColor = MaterialTheme.colors.secondary,
)

@Composable
fun ButtonDefaults.primary() = buttonColors()

@Composable
fun ButtonDefaults.error() = buttonColors(
    backgroundColor = MaterialTheme.colors.error
)