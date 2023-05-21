package likco.likfit.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun Login(modifier: Modifier) = Column(modifier = modifier) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    fun login() {

    }

    OutlinedTextField(login, { login = it })
    OutlinedTextField(password, { password = it })

    Button(onClick = ::login) {
        Text("Continue")
    }
}