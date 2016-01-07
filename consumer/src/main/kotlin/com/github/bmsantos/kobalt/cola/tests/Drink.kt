package com.github.bmsantos.kobalt.cola.tests

import kotlin.collections.hashMapOf

abstract class Drink(val volume: Double) {
    companion object {
        val drinks = hashMapOf(
                "water" to 0.0,
                "beer" to 7.0,
                "wine" to 12.5,
                "juice" to 0.1,
                "kombucha" to 0.5,
                "cachaca" to 54.0,
                "port" to 25.0,
                "sake" to 15.0,
                "tequila" to 40.5,
                "rum" to 37.5,
                "vodka" to 50.0,
                "ouzo" to 37.5,
                "gin" to 50.0,
                "kefir" to 1.0,
                "cider" to 2.0)
    }
}