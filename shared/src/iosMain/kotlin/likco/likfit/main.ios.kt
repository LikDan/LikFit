package likco.likfit


import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import moe.tlaster.precompose.PreComposeApplication
import platform.CoreGraphics.CGFloat

fun MainViewController(top: CGFloat, bottom: CGFloat) = PreComposeApplication("LikFit") {
    App(Modifier.padding(top = top.dp, bottom = bottom.dp))
}
