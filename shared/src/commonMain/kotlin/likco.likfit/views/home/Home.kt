package likco.likfit.views.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import likco.likfit.models.Activity
import likco.likfit.services.ActivitiesService
import likco.likfit.services.ui.Navigator
import likco.likfit.utils.viewmodels.sharedViewModel
import likco.likfit.viewmodels.ActivityViewModel
import likco.likfit.viewmodels.StepsCounterViewModel
import likco.likfit.viewmodels.UserViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Home() = Column(
    modifier = Modifier.padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    val user by sharedViewModel<UserViewModel>()()

    val stepsViewModel = sharedViewModel<StepsCounterViewModel>()
    val steps by stepsViewModel()

    val activityViewModel = sharedViewModel<ActivityViewModel>()
    val activity by activityViewModel()

    stepsViewModel.activityViewModel = activityViewModel

    Row(verticalAlignment = Alignment.CenterVertically,) {
        Card(
            backgroundColor = MaterialTheme.colors.primary,
            shape = CircleShape,
            modifier = Modifier
                .size(136.dp)
                .clickable { Navigator("activity/steps") }
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource("steps.xml"),
                        contentDescription = "steps",
                        modifier = Modifier.size(42.dp)
                    )
                    if (activity == null) Text(steps.total.toString()) else Text("${steps.total}\n${steps.afterReset}")
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = painterResource("calories.xml"),
                        contentDescription = "steps",
                        modifier = Modifier.size(42.dp, 28.dp)
                    )
                    Text(activity?.totalCalories?.toString() ?: "-")
                }
            }
        }

        TextButton(onClick = { Navigator("profile") }) {
            Text(user?.login ?: "-?-", style = MaterialTheme.typography.h4)
        }
    }

    var activities by remember { mutableStateOf(listOf<Activity>()) }
    if (activities.isEmpty()) ActivitiesService.index {
        activities = it
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(activities) {
            ActivityCard(
                activity = it,
                selected = activity == it,
                onStart = {
                    activityViewModel.start(it)
                    stepsViewModel.reset()
                },
                onEnd = { activityViewModel.stop() },
                onCurrentInfo = { Navigator("activity/current") },
                onInfo = { Navigator("activity/info/${it.name}") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}