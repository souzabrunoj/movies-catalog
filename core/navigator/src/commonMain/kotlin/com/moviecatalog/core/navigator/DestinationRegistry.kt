package com.moviecatalog.core.navigator

import cafe.adriel.voyager.core.screen.Screen

/**
 * Central registry mapping [RootDestination] to Voyager [Screen] factories (same role as [ScreenRegistry] in mobile-banking-android).
 *
 * Populate during app startup (e.g. Koin) on a single thread; avoid concurrent register/read on Kotlin/Native.
 */
public class DestinationRegistry {
    private val factories = mutableMapOf<RootDestination, () -> Screen>()

    public fun register(destination: RootDestination, factory: () -> Screen) {
        val previous = factories.put(destination, factory)
        if (previous != null) {
            throw DestinationRegisterException("Destination $destination was already registered.")
        }
    }

    public fun registerAll(entries: Map<RootDestination, () -> Screen>) {
        entries.forEach { (destination, factory) -> register(destination, factory) }
    }

    /** Overwrites an existing factory (same idea as [ScreenRegistry.replaceScreen] in mobile-banking-android). */
    public fun replaceDestination(destination: RootDestination, factory: () -> Screen) {
        factories[destination] = factory
    }

    public fun createScreen(destination: RootDestination): Screen {
        val factory = factories[destination]
        return factory?.invoke()
            ?: throw DestinationRegisterException("No factory registered for $destination.")
    }

    public fun isRegistered(destination: RootDestination): Boolean = destination in factories
}
