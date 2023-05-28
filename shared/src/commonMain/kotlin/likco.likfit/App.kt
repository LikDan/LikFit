package likco.likfit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import likco.likfit.i18n.Languages
import likco.likfit.i18n.Strings
import likco.likfit.modals.ui.SnackBar
import likco.likfit.modals.ui.SnackBarType
import likco.likfit.services.AuthService
import likco.likfit.services.firebase.FirebaseAuth
import likco.likfit.services.ui.SnackBarHandler

@Composable
fun App() {
    MaterialTheme {
        var language by remember { mutableStateOf(Languages.EN_US) }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            var login by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var user by remember { mutableStateOf(AuthService.user) }

            fun launchSnackBar() {
                val value = SnackBarType.values().random()
                SnackBarHandler.show(SnackBar(value.name, value))
            }

            Text("Current language is $language")

            Button(::launchSnackBar) {
                Text("Show Snackbar")
            }

            OutlinedTextField(login, { login = it })
            OutlinedTextField(password, { password = it })

            Button(onClick = {
                AuthService.login(login, password) {
                    user = FirebaseAuth.user
                }
            }) {
                Text(language.getOrCode(Strings.LOGIN))
            }

            Button(onClick = {
                AuthService.signup(login, password, password) {
                    user = FirebaseAuth.user
                }
            }) {
                Text(language.getOrCode(Strings.SIGNUP))
            }

            Button(onClick = {
                AuthService.logout {
                    user = FirebaseAuth.user
                }
            }) {
                Text(language.getOrCode(Strings.LOGOUT))
            }

            Button(onClick = {
                Strings.locale = Languages.EN_US
                language = Strings.locale
            }) {
                Text("English")
            }

            Button(onClick = {
                Strings.locale = Languages.RU_RU
                language = Strings.locale
            }) {
                Text("Russian")
            }

            Text(user?.login ?: "not authenticated")

            SnackBarHandler.view()
        }
    }
}
