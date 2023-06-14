package likco.likfit.views.activities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import likco.likfit.models.Activity
import likco.likfit.models.UserActivity
import likco.likfit.services.UserActivitiesService
import likco.likfit.services.firebase.firestore.FirestoreOperationsType
import likco.likfit.services.ui.Navigator
import likco.likfit.utils.toTimeString
import likco.likfit.utils.toUSString
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalResourceApi::class)
@Composable
fun ActivityInfo(activity: Activity) = Column(
    verticalArrangement = Arrangement.spacedBy(16.dp),
    modifier = Modifier.padding(horizontal = 16.dp)
) {
    Row(horizontalArrangement = Arrangement.Center) {
        Button(onClick = { Navigator("home") }) {
            Icon(Icons.Default.ArrowBack, "back")
        }
        Text(
            activity.name,
            style = MaterialTheme.typography.h4,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(1f)
        )
    }
    Text(activity.description)

    var history by remember { mutableStateOf(emptyList<UserActivity>()) }
    if (history.isEmpty()) UserActivitiesService.index(
        listOf(
            Triple(
                "name",
                FirestoreOperationsType.EQUAL,
                activity.name
            )
        )
    ) {
        history = it
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(history) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column(verticalArrangement = Arrangement.SpaceBetween) {
                        Text(it.timeStart.toUSString())
                        Text(it.totalSeconds.seconds.toTimeString())
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(it.totalSteps.toString())
                            Icon(
                                painter = painterResource("steps.xml"),
                                contentDescription = "steps",
                                modifier = Modifier.size(42.dp)
                            )
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            Text(it.totalCalories.roundToInt().toString())
                            Icon(
                                painter = painterResource("calories.xml"),
                                contentDescription = "steps",
                                modifier = Modifier.size(42.dp, 28.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}