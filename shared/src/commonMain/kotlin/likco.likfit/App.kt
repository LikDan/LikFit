package likco.likfit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import likco.likfit.services.ui.Navigator
import likco.likfit.services.ui.SnackBarHandler
import likco.likfit.theme.LikFitTheme
import likco.likfit.utils.viewmodels.makeShared
import likco.likfit.utils.viewmodels.simpleViewModel
import likco.likfit.viewmodels.I18nViewModel
import likco.likfit.viewmodels.StepsCounterViewModel
import likco.likfit.viewmodels.UserViewModel
import likco.likfit.views.login.Login
import likco.likfit.views.profile.Profile

@Composable
fun App(modifier: Modifier = Modifier) {
    LikFitTheme(modifier) {
        simpleViewModel(I18nViewModel::class) { I18nViewModel() }.makeShared()
        simpleViewModel(UserViewModel::class) { UserViewModel() }.makeShared()
        simpleViewModel(StepsCounterViewModel::class) { StepsCounterViewModel() }.makeShared()

        Navigator.view(
            initial = "auth/login",
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            scene(route = "auth/login") { Login() }
            scene(route = "profile") { Profile() }

            scene(route = "home") { Surface(modifier = Modifier.fillMaxSize()) { Text("home") } }
        }

        SnackBarHandler.view(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
