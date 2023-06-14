package likco.likfit

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import likco.likfit.services.AndroidStepsCounterService
import likco.likfit.services.stepscounter.StepsCounterServiceNative
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent


class MainActivity : PreComposeActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

//        StepsCounterServiceNative.nativeStart = {
//            startService(Intent(this, AndroidStepsCounterService::class.java))
//        }
//
//        StepsCounterServiceNative.nativeStop = {
//            stopService(Intent(this, AndroidStepsCounterService::class.java))
//        }

        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)
        setContent {
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId <= 0) return@setContent MainView(0f, prefs)

            val dpi = resources.displayMetrics.densityDpi.toFloat()
            val metrics = DisplayMetrics.DENSITY_DEFAULT

            val height = resources.getDimensionPixelSize(resourceId) / dpi * metrics
            MainView(height, prefs)
        }
    }
}