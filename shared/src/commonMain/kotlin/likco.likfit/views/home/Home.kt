package likco.likfit.views.home

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import likco.likfit.services.ui.Navigator

@Composable
fun Home() {
    Button(onClick = { Navigator.navigate("profile") }) {
        Text("Profile")
    }
}