package likco.likfit.utils.viewmodels

import androidx.compose.runtime.Composable
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModel
import kotlin.reflect.KClass

//@Composable
//inline fun <reified T : ViewModel> simpleViewModel(noinline creator: () -> T): T =
//    simpleViewModel(T::class, creator)

@Composable
fun <T : ViewModel> simpleViewModel(cls: KClass<T>, creator: () -> T): T =
    viewModel(cls) { creator() }
