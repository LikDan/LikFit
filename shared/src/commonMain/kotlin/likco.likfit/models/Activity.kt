package likco.likfit.models

import kotlinx.datetime.LocalDateTime
import likco.likfit.services.firebase.firestore.IFirestoreDocumentMapper
import likco.likfit.services.firebase.firestore.RawFirestoreDocument
import likco.likfit.utils.minus
import likco.likfit.utils.now
import likco.likfit.utils.toTimeString
import kotlin.math.roundToInt
import kotlin.time.Duration
import kotlin.time.DurationUnit

data class Activity(
    val name: String,
    val description: String,
    val icon: String,
    val caloriesOnSecond: Float
) {
    var timeStart: LocalDateTime? = null
    var trainingTime: Duration? = null
    var totalCaloriesAccuracy: Float = 0f
    var trainingSteps: Int = 0

    val trainingTimeText: String
        get() = this.trainingTime?.toTimeString() ?: "-"

    val totalSeconds: Long
        get() = this.trainingTime?.toLong(DurationUnit.SECONDS) ?: 0L

    val totalCalories: Int
        get() = totalCaloriesAccuracy.roundToInt()

    fun userActivity(steps: Int): UserActivity = UserActivity(
        this.name,
        this.totalSeconds,
        this.timeStart!!,
        this.totalCaloriesAccuracy,
        steps
    )

    fun start() {
        this.timeStart = LocalDateTime.now()
    }

    fun stop() {
        this.timeStart ?: return
        this.trainingTime = LocalDateTime.now().minus(this.timeStart!!)
    }

    fun update(steps: Int) {
        this.timeStart ?: return
        this.trainingTime = LocalDateTime.now().minus(this.timeStart!!)
        this.trainingSteps += steps

        this.totalCaloriesAccuracy =
            caloriesOnSecond * this.trainingTime!!.toLong(DurationUnit.SECONDS)
    }

    companion object : IFirestoreDocumentMapper<Activity> {
        override fun from(document: RawFirestoreDocument): Activity {
            return Activity(
                document["name"] as? String ?: "",
                document["description"] as? String ?: "",
                document["icon"] as? String ?: "",
                document["caloriesOnSecond"].toString().toFloatOrNull() ?: 0f,
            )
        }

        override fun to(document: Activity): RawFirestoreDocument {
            return mapOf(
                "name" to document.name,
                "description" to document.description,
                "icon" to document.icon,
                "caloriesOnSecond" to document.caloriesOnSecond,
            )
        }
    }

}