package likco.likfit

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import moe.tlaster.precompose.lifecycle.PreComposeActivity
import moe.tlaster.precompose.lifecycle.setContent


class MainActivity : PreComposeActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
            if (resourceId <= 0) return@setContent MainView(0f)

            val dpi = resources.displayMetrics.densityDpi.toFloat()
            val metrics = DisplayMetrics.DENSITY_DEFAULT

            val height = resources.getDimensionPixelSize(resourceId) / dpi * metrics
            MainView(height)
        }
    }
}