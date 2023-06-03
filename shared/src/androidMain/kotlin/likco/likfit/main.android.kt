package likco.likfit

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainView(height: Float) {
    TransparentSystemBars()
    App(Modifier.padding(top = height.dp))
}
