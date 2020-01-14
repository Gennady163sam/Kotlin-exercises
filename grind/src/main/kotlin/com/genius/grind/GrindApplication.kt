package com.genius.grind

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class GrindApplication

fun main(args: Array<String>) {
    runApplication<GrindApplication>(*args)
}
