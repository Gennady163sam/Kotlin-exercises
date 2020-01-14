package com.genius.grind.components

import org.springframework.stereotype.Component

@Component
class InfoDistributor(var unitStore: UnitStore, var worldDispatcher: WorldDispatcher) {

    fun status() : String {
        return "Current status = \n" +
                "Ypu have " + worldDispatcher.player.balance + "$\n" +
                "army = " + worldDispatcher.player.army + " \n" +
                "hours = " + worldDispatcher.hours + "\n" +
                "You can buy:" +
                unitStore.range()
    }
}