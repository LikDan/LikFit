@file:Suppress("DEPRECATED_SYNTAX_WITH_DEFINITELY_NOT_NULL")

package likco.likfit.utils.viewmodels

import moe.tlaster.precompose.viewmodel.ViewModel
import kotlin.reflect.KClass

private val viewModels: MutableMap<KClass<ViewModel>, ViewModel> = mutableMapOf()

fun <T: ViewModel> T.makeShared() = apply {
    viewModels[this::class as KClass<ViewModel>] = this
}

inline fun <reified T: ViewModel> sharedViewModel(): T = sharedViewModel(T::class as KClass<ViewModel>)!! as T

fun <T: ViewModel> sharedViewModel(modelClass: KClass<T>): T {
    return viewModels[modelClass as KClass<ViewModel>] as T!!
}
