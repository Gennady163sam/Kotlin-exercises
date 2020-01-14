package com.genius.grind.domain

class Player(var name: String, var balance: Long, var army: MutableList<Mob>) {

    open fun addUnit(mob: Mob) {
        this.army.add(mob);
    }

    open fun calculateNewBalance() {
        balance += army.map { u -> u.profit }.sum()
    }

    fun getArmyInfo() {
        army.groupBy { Mob::name }
    }
}