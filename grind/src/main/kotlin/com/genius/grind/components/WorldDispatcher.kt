package com.genius.grind.components

import com.genius.grind.domain.Player
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class WorldDispatcher {
    var player: Player = Player("User1", 10, ArrayList())
    var hours: Long = 0

    @Scheduled(fixedRate = 1000)
    fun hour() {
        hours++;
        player.calculateNewBalance()
    }
}