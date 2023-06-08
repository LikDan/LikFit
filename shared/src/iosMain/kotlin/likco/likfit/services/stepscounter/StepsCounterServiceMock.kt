package likco.likfit.services.stepscounter

import likco.likfit.services.Test
import platform.posix.sleep
import kotlin.native.concurrent.TransferMode
import kotlin.native.concurrent.Worker
import kotlin.random.Random

actual object StepsCounterServiceNative {
    private var stopped = true

    actual fun start() {
        if (!stopped) return
        this.stopped = false

        val worker = Worker.start()
        worker.execute(TransferMode.SAFE, { this to worker }) {
            while (!it.first.stopped) {
                update(Random.nextInt(25))
                sleep(5)
            }
        }
    }

    actual fun stop() {
        this.stopped = true
    }

    actual fun update(steps: Int) = Test.update(steps)
}