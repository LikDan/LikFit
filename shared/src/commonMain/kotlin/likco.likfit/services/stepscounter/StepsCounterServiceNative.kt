package likco.likfit.services.stepscounter

expect object StepsCounterServiceNative {
    fun start()
    fun stop()

    fun update(steps: Int)
}