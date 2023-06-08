package likco.likfit.services

object NativeStepCounterService {
    fun update(steps: Int) = StepsCounterService.update(steps)
}