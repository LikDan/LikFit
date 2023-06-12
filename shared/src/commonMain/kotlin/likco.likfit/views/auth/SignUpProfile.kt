package likco.likfit.views.auth

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
import androidx.compose.ui.focus.onFocusChanged
import kotlinx.datetime.LocalDate
import likco.likfit.i18n.Strings
import likco.likfit.models.Gender
import likco.likfit.services.ui.Navigator
import likco.likfit.ui.Form
import likco.likfit.ui.GenderSelection
import likco.likfit.utils.parseUS
import likco.likfit.utils.viewmodels.sharedViewModel
import likco.likfit.viewmodels.UserViewModel

@Composable
fun SignUpProfile() = Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
    val viewModel = sharedViewModel<UserViewModel>()

    var height by remember { mutableStateOf<Float?>(null) }
    var weight by remember { mutableStateOf<Float?>(null) }
    var birthday by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf<Gender?>(null) }

    var showGenderSelection by remember { mutableStateOf(false) }
    if (showGenderSelection) {
        Form(Strings.GENDER_SELECTION, showLangToggle = false) { i18n ->
            GenderSelection(i18n = i18n, back = { showGenderSelection = false }) {
                showGenderSelection = false
                gender = it
            }
        }
    } else {
        Form(
            title = Strings.PROFILE,
            onSubmit = {
                val birthdayLocalDate = LocalDate.parseUS(birthday)
                viewModel.createProfile(height, weight, birthdayLocalDate, gender) {
                    Navigator.navigate("home")
                }
            },
            showLangToggle = true,
        ) { i18n ->
            OutlinedTextField(
                value = height?.toString() ?: "",
                onValueChange = { height = it.toFloatOrNull() ?: height },
                label = { Text(i18n(Strings.HEIGHT)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = weight?.toString() ?: "",
                onValueChange = { weight = it.toFloatOrNull() ?: weight },
                label = { Text(i18n(Strings.WEIGHT)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = birthday,
                onValueChange = { birthday = it },
                label = { Text(i18n(Strings.BIRTHDAY)) },
                placeholder = { Text("mm-dd-yyyy") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = i18n(gender?.toString() ?: ""),
                onValueChange = { },
                label = { Text(i18n(Strings.GENDER)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { if (it.isFocused) showGenderSelection = true },
            )
        }
    }
}