package com.genius.grind.controllers

import com.genius.grind.components.InfoDistributor
import com.genius.grind.components.UnitStore
import com.genius.grind.components.WorldDispatcher
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

@RestController
@RequestMapping("world")
class RestController(var worldDispatcher: WorldDispatcher, var unitStore: UnitStore, var infoDistributor: InfoDistributor) {

    @GetMapping("/status")
    fun currentStatus() = infoDistributor.status()

    @GetMapping("/buy")
    fun buyUnit(@RequestParam unit : String) : ResponseEntity<String> {
        try {
            unitStore.buy(worldDispatcher.player, unit)
        } catch (ex : RuntimeException) {
            return ResponseEntity(ex.message, HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity.ok(infoDistributor.status())
    }
}