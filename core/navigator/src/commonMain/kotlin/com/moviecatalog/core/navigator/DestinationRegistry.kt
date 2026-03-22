package com.moviecatalog.core.navigator

import com.moviecatalog.core.navigator.step.Step

public class DestinationRegistry {
    private val factories = mutableMapOf<NavDestination, () -> Step>()

    public fun register(destination: NavDestination, factory: () -> Step) {
        val previous = factories.put(destination, factory)
        if (previous != null) {
            throw DestinationRegisterException("Destination $destination was already registered.")
        }
    }

    public fun <D : NavDestination> registerAll(entries: Map<D, () -> Step>) {
        entries.forEach { (destination, factory) -> register(destination, factory) }
    }

    public fun replaceDestination(destination: NavDestination, factory: () -> Step) {
        factories[destination] = factory
    }

    public fun createStep(destination: NavDestination): Step {
        val factory = factories[destination]
        return factory?.invoke()
            ?: throw DestinationRegisterException("No factory registered for $destination.")
    }

    public fun isRegistered(destination: NavDestination): Boolean = destination in factories
}
