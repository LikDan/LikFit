package likco.likfit.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun LikFitTheme(content: @Composable () -> Unit) = MaterialTheme(
    colors = colors,
    content = content
)