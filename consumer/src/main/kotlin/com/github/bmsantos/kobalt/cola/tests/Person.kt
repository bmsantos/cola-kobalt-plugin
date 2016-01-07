package com.github.bmsantos.kobalt.cola.tests

import com.github.bmsantos.kobalt.cola.tests.Drink.Companion.drinks

data class Person(var totalAlcoholVolume: Double = 0.0) {
    fun drink(name: String) {
        totalAlcoholVolume += drinks.getOrDefault(name, 0.0)
    }
}
