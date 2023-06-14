package likco.likfit.views.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import likco.likfit.services.ui.Navigator
import likco.likfit.utils.viewmodels.sharedViewModel
import likco.likfit.viewmodels.ActivityViewModel
import likco.likfit.viewmodels.StepsCounterViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CurrentActivityInfo() = Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    modifier = Modifier.padding(horizontal = 16.dp)
) {
    val steps by sharedViewModel<StepsCounterViewModel>()()
    val activity by sharedViewModel<ActivityViewModel>()()
    Row(horizontalArrangement = Arrangement.Center) {
        Button(onClick = { Navigator("home") }) {
            Icon(Icons.Default.ArrowBack, "back")
        }
        Text(
            activity?.name ?: "-?-",
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
    Text(activity?.description ?: "-?-")

    Card(modifier = Modifier.weight(1f).fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(
                activity?.trainingTimeText ?: "-?-",
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.weight(1f))
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource("steps.xml"),
                        contentDescription = "steps",
                        modifier = Modifier.size(128.dp)
                    )
                    Text(
                        steps.current.toString(),
                        style = MaterialTheme.typography.h2,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource("steps.xml"),
                        contentDescription = "steps",
                        modifier = Modifier.size(128.dp)
                    )
                    Text(
                        steps.afterReset.toString(),
                        style = MaterialTheme.typography.h2,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource("calories.xml"),
                        contentDescription = "calories",
                        modifier = Modifier.size(128.dp, 108.dp)
                    )
                    Text(
                        activity?.totalCalories?.toString() ?: "-?-",
                        style = MaterialTheme.typography.h2,
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
