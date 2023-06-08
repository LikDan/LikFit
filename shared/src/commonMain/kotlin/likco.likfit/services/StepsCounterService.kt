package likco.likfit.services

import likco.likfit.viewmodels.StepsCounterViewModel

object StepsCounterService {
    lateinit var viewModel: StepsCounterViewModel

    fun update(steps: Int) {
        viewModel.update(steps)
    }
}