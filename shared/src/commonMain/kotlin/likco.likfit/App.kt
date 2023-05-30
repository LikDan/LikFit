package likco.likfit

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import likco.likfit.services.ui.Navigator
import likco.likfit.utils.viewmodels.makeShared
import likco.likfit.utils.viewmodels.simpleViewModel
import likco.likfit.viewmodels.I18nViewModel
import likco.likfit.viewmodels.UserViewModel
import likco.likfit.views.login.Login
import likco.likfit.views.profile.Profile

@Composable
fun App() {
    MaterialTheme {
        Column {
            simpleViewModel(I18nViewModel::class) { I18nViewModel() }.makeShared()
            simpleViewModel(UserViewModel::class) { UserViewModel() }.makeShared()

            Navigator.view(initial = "auth/login") {
                scene(route = "auth/login") { Login() }
                scene(route = "profile") { Profile() }

                scene(route = "home") { Text("home") }
            }
        }
    }
}
