package likco.likfit.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import likco.likfit.utils.viewmodels.makeShared
import likco.likfit.utils.viewmodels.simpleViewModel
import likco.likfit.viewmodels.ThemeViewModel
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun LikFitTheme(modifier: Modifier, content: @Composable BoxScope.() -> Unit) =
    //todo NavHost - fix view models update
    NavHost(
        navigator = rememberNavigator(),
        initialRoute = "app",
        modifier = Modifier.fillMaxSize()
    ) {
        scene(route = "app") {
            val initialDarkTheme = isSystemInDarkTheme()
            val theme by simpleViewModel(ThemeViewModel::class) {
                ThemeViewModel(if (initialDarkTheme) Themes.DARK else Themes.LIGHT)
            }.makeShared()()

            MaterialTheme(
                colors = theme.colors,
                shapes = theme.shapes,
                typography = theme.typography
            ) {
                CompositionLocalProvider(LocalContentColor provides MaterialTheme.colors.onBackground) {
                    Box(
                        content = content,
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .padding(vertical = 15.dp)
                            .fillMaxSize()
                            .then(modifier),
                    )
                }
            }
        }
    }