package com.moviecatalog.core.navigator

import com.moviecatalog.core.navigator.step.Step
import org.koin.core.Koin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

public fun <D : NavDestination> Module.importDestinations(vararg entries: Pair<D, () -> Step>) {
    importDestinations(entries.toMap())
}

public fun <D : NavDestination> Module.importDestinations(map: Map<D, () -> Step>) {
    val frozen = map.toMap()
    if (frozen.isEmpty()) return
    val qualifier = named(
        "destinationImport|" +
            frozen.keys.map { it::class.simpleName ?: it.toString() }.sorted().joinToString("|"),
    )
    single(qualifier = qualifier, createdAtStart = true) {
        get<DestinationRegistry>().registerAll(frozen)
    }
}

public fun <D : NavDestination> Koin.replaceDestination(pair: Pair<D, () -> Step>) {
    get<DestinationRegistry>().replaceDestination(pair.first, pair.second)
}

public val navigatorRegistryModule: Module = module {
    single { DestinationRegistry() }
}
