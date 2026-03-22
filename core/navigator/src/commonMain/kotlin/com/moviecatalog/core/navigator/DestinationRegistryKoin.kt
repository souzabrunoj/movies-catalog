package com.moviecatalog.core.navigator

import cafe.adriel.voyager.core.screen.Screen
import org.koin.core.Koin
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Koin equivalent of mobile-banking-android `importScreens` + [ScreenRegistry] wiring
 * (`onReady { instance<ScreenRegistry>().registerScreens(...) }`).
 *
 * Uses [createdAtStart] so registration runs when Koin starts (no `onKoinStarted` — that API lives
 * outside plain `koin-core` / differs by version). Each call gets a distinct [named] qualifier.
 */
public fun Module.importDestinations(vararg entries: Pair<RootDestination, () -> Screen>) {
    importDestinations(entries.toMap())
}

public fun Module.importDestinations(map: Map<RootDestination, () -> Screen>) {
    val frozen = map.toMap()
    if (frozen.isEmpty()) return
    val qualifier = named(
        "destinationImport|" + frozen.keys.map { it::class.simpleName ?: it.toString() }.sorted().joinToString("|"),
    )
    single(qualifier = qualifier, createdAtStart = true) {
        get<DestinationRegistry>().registerAll(frozen)
    }
}

/** Runtime swap of a destination factory (like `DI.replaceScreen`). Call as `getKoin().replaceDestination(...)`. */
public fun Koin.replaceDestination(pair: Pair<RootDestination, () -> Screen>) {
    get<DestinationRegistry>().replaceDestination(pair.first, pair.second)
}

/**
 * Singleton registry; feature modules call [importDestinations] to contribute factories.
 */
public val navigatorRegistryModule: Module = module {
    single { DestinationRegistry() }
}
