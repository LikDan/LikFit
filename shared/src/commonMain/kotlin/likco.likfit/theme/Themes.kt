package likco.likfit.theme

import androidx.compose.material.Colors
import androidx.compose.material.Shapes
import androidx.compose.material.Typography

enum class Themes(val colors: Colors, val shapes: Shapes, val typography: Typography) {
    LIGHT(colors = lightColors, shapes = shapes, typography = typography),
    DARK(colors = darkColors, shapes = shapes, typography = typography)
}