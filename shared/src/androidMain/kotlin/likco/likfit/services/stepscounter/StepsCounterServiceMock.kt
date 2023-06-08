package likco.likfit.services.stepscounter

import likco.likfit.services.Test
import kotlin.concurrent.thread
import kotlin.random.Random

actual object StepsCounterServiceNative {
    private var stopped = true

    actual fun start() {
        if (!stopped) return

        thread {
            this.stopped = false
            while (!this.stopped) {
                update(Random.nextInt(25))
                Thread.sleep(5000L)
            }
        }
    }

    actual fun stop() {
        this.stopped = true
    }

    actual fun update(steps: Int) = Test.update(steps)
}