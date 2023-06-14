package likco.likfit.viewmodels

import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.atTime
import kotlinx.datetime.toLocalDateTime
import likco.likfit.models.StepsCounter
import likco.likfit.models.UserSteps
import likco.likfit.services.UserStepsService
import likco.likfit.services.stepscounter.StepsCounterServiceNative
import likco.likfit.utils.timezone
import likco.likfit.utils.viewmodels.StateViewModel

class StepsCounterViewModel : StateViewModel<StepsCounter>(StepsCounter(0, 0, 0)) {
    var activityViewModel: ActivityViewModel? = null

    fun start() {
        StepsCounterServiceNative.start()
    }

    fun stop() {
        StepsCounterServiceNative.stop()
    }

    fun reset() = mState.update {
        it.copy(afterReset = 0)
    }

    fun update(steps: Int) {
        val now = Clock.System.now().toLocalDateTime(LocalDateTime.timezone).date
        UserStepsService.create(
            now.toString(),
            UserSteps(now, value.total + steps)
        ) {}
        mState.update {
            it.copy(
                total = it.total + steps,
                current = steps,
                afterReset = it.afterReset + steps
            )
        }

        activityViewModel?.update(steps)
    }

    init {
        val date = Clock.System.now().toLocalDateTime(LocalDateTime.timezone).date
        UserStepsService.get(date.toString()) {
            this.update(it.steps)
        }
    }
}