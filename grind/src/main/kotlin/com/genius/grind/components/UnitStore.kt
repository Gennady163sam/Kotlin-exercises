package com.genius.grind.components

import com.genius.grind.domain.Mob
import com.genius.grind.domain.Player
import org.springframework.stereotype.Component
import java.lang.RuntimeException
import javax.annotation.PostConstruct

@Component
class UnitStore {
    var unitStore: MutableMap<String, Mob> = HashMap()

    @PostConstruct
    fun initStore() {
        unitStore.put("Peasant", Mob(10, "Peasant", 10, "Peasants work in the fields and bring 10 gold per hour"))
        unitStore.put("Thief", Mob(50, "Thief", 70, "Thieves steal goods that you can sell later"))
        unitStore.put("Rogue", Mob(500, "Rogue", 180, "Rogues robbed for you"))
        unitStore.put("Knight", Mob(1000, "Knight", 360, "Knights safe you and earn money"))
    }

    fun range(): List<String> {
        return unitStore.values.map { u -> u.name + " - " + u.cost + "$."}
    }

    fun buy(player: Player, unitName: String) {
        val unit = unitStore.get(unitName)
        if (unit == null) {
            throw RuntimeException("Not found unit with name = " + unitName)
        }
        if (player.balance >= unit.cost) {
            player.balance = player.balance - unit.cost
            player.addUnit(unit)
        } else {
            throw RuntimeException("You don't have enough money for this unit!")
        }
    }
}