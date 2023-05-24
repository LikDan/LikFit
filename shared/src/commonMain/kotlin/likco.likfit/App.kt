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
import likco.likfit.services.AuthService
import likco.likfit.services.firebase.FirebaseAuth

@Composable
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

            var login by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var user by remember { mutableStateOf("") }

            OutlinedTextField(login, { login = it })
            OutlinedTextField(password, { password = it })

            Button(onClick = {
                AuthService.login(login, password) {
                    user = FirebaseAuth.user?.login ?: "Not logged in"
                }
            }) {
                Text("Login")
            }

            Button(onClick = {
                AuthService.signup(login, password, password) {
                    user = FirebaseAuth.user?.login ?: "Not logged in"
                }
            }) {
                Text("SignUp")
            }
            Text(user)
        }
    }
}
