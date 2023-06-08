package likco.likfit.services

import likco.likfit.viewmodels.StepsCounterViewModel

object Test {
    lateinit var viewModel: StepsCounterViewModel

    fun update(steps: Int) {
        viewModel.update(steps)
    }
}