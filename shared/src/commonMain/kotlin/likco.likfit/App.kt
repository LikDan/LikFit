package likco.likfit

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import likco.likfit.views.auth.Login
import likco.likfit.views.auth.SignUp
import likco.likfit.views.auth.SignUpProfile
import likco.likfit.views.home.Home
import likco.likfit.views.profile.Profile

@Composable
fun App(modifier: Modifier = Modifier) {
    LikFitTheme(modifier) {
        simpleViewModel(I18nViewModel::class) { I18nViewModel() }.makeShared()
        val userViewModel = simpleViewModel(UserViewModel::class) { UserViewModel() }.makeShared()
        simpleViewModel(StepsCounterViewModel::class) { StepsCounterViewModel() }.makeShared()

        var loading by remember { mutableStateOf(true) }
        userViewModel.loadProfile { loading = false }
        val user = userViewModel().value

        if (loading) {
            Text("Loading")
            return@LikFitTheme
        }

        val initial = when {
            user == null -> "auth/login"
            user.profile == null -> "auth/profile"
            else -> "home"
        }

        Navigator.view(
            initial = initial,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
        ) {
            scene(route = "auth/login") { Login() }
            scene(route = "auth/signup") { SignUp() }
            scene(route = "auth/profile") { SignUpProfile() }

            scene(route = "home") { Home() }
            scene(route = "profile") { Profile() }
        }

        SnackBarHandler.view(
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}
