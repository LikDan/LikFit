package likco.likfit.views.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import likco.likfit.i18n.Strings
import likco.likfit.services.ui.Navigator
import likco.likfit.ui.Form
import likco.likfit.utils.viewmodels.sharedViewModel
import likco.likfit.viewmodels.UserViewModel

@Composable
fun SignUp() = Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    val viewModel = sharedViewModel<UserViewModel>()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeat by remember { mutableStateOf("") }

    Form(
        title = Strings.SIGNUP,
        submitButtonText = Strings.SIGNUP,
        onSubmit = { viewModel.signup(email, password, repeat) { Navigator("auth/profile") } },
        showLangToggle = true,
        bottom = { i18n ->
            Text(
                text = i18n(Strings.LOGIN),
                modifier = Modifier
                    .clickable { Navigator("auth/login") }
                    .align(Alignment.CenterHorizontally)
            )
        }
    ) { i18n ->
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(i18n(Strings.LOGIN)) },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(i18n(Strings.PASSWORD)) },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = repeat,
            onValueChange = { repeat = it },
            label = { Text(i18n(Strings.PASSWORD_REPEAT)) },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
