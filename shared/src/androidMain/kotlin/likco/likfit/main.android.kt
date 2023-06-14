package likco.likfit

import android.content.SharedPreferences
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import likco.likfit.services.DataStoreService

@Composable
fun MainView(height: Float, preferences: SharedPreferences) {
    DataStoreService.preferences = preferences
    TransparentSystemBars()
    App(Modifier.padding(top = height.dp))
}
