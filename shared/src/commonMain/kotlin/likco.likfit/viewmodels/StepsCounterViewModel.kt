package likco.likfit.viewmodels

import kotlinx.coroutines.flow.update
import likco.likfit.models.StepsCounter
import likco.likfit.utils.viewmodels.StateViewModel

class StepsCounterViewModel : StateViewModel<StepsCounter>(StepsCounter(0, 0, 0)) {
    fun update(steps: Int) = mState.update {
        it.copy(
            total = it.total + steps,
            current = steps,
            afterReset = it.afterReset + steps
        )
    }
}