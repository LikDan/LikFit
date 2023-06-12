package likco.likfit.services.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.RouteBuilder
import moe.tlaster.precompose.navigation.rememberNavigator

object Navigator {
    private var navigator: Navigator? = null

    fun navigate(route: String, options: NavOptions? = null) =
        this.navigator?.navigate(route, options)

    operator fun invoke(route: String, options: NavOptions? = null) = navigate(route, options)

    @Composable
    fun view(modifier: Modifier = Modifier, initial: String, builder: RouteBuilder.() -> Unit) {
        this.navigator = rememberNavigator()
        NavHost(
            navigator = this.navigator!!,
            initialRoute = initial,
            builder = builder,
            modifier = modifier
        )
    }
}