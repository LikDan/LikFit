package likco.likfit.viewmodels

import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import likco.likfit.models.Activity
import likco.likfit.services.StepsCounterService
import likco.likfit.services.UserActivitiesService
import likco.likfit.utils.viewmodels.StateViewModel

class ActivityViewModel(initial: Activity? = null) : StateViewModel<Activity?>(initial) {
    fun start(activity: Activity) {
        activity.start()
        mState.update { activity }
    }

    fun stop() {
        value?.stop()
        if (value != null) {
            val id = Clock.System.now().toString()
            val steps = StepsCounterService.viewModel.value.afterReset
            UserActivitiesService.create(id, value!!.userActivity(steps)) {}
        }
        mState.update { null }
    }

    fun update(steps: Int) = mState.update {
        it?.update(steps)
        it
    }
}