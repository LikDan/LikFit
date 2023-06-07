package likco.likfit.views.login

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import likco.likfit.i18n.Strings
import likco.likfit.models.Profile
import likco.likfit.services.ProfileService
import likco.likfit.services.ui.Navigator
import likco.likfit.services.ui.SnackBarHandler
import likco.likfit.utils.viewmodels.sharedViewModel
import likco.likfit.viewmodels.I18nViewModel
import likco.likfit.viewmodels.ThemeViewModel
import likco.likfit.viewmodels.UserViewModel
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun Login() = Column {
    var profile by remember { mutableStateOf<Profile?>(null) }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel = viewModel(UserViewModel::class) { UserViewModel() }
    val uiState by viewModel()

    val i18nViewModel = sharedViewModel<I18nViewModel>()
    val i18n by i18nViewModel()

    val darkTheme = sharedViewModel<ThemeViewModel>()

    Text(uiState?.login ?: "")

    OutlinedTextField(email, { email = it })
    OutlinedTextField(password, { password = it })

    Button(onClick = { viewModel.login(email, password) }) {
        Text(i18n.getOrCode(Strings.LOGIN))
    }

    Button(onClick = { i18nViewModel.toggle() }) {
        Text("Toggle lang")
    }

    Button(onClick = { darkTheme.toggle() }) {
        Text("Toggle theme")
    }

    Button(onClick = { Navigator.navigate("profile") }) {
        Text("To profile")
    }

    Button(onClick = { Navigator.navigate("home") }) {
        Text("To home")
    }

    Text("Firestore")
    Text(profile?.name ?: "no profile")

    Button(onClick = { ProfileService.create(Profile("user")) { SnackBarHandler.success("created") } }) {
        Text("Firestore create")
    }

    Button(onClick = { ProfileService.get { profile = it } }) {
        Text("Firestore get")
    }

    Button(onClick = { ProfileService.delete { SnackBarHandler.success("created") } }) {
        Text("Firestore delete")
    }

//    SnackBarHandler.view()
}