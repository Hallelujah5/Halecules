package com.example.cards

class Card(private val rank: String, private val suit: String, var faceUp: Boolean = true) {
    fun flip() {
        faceUp = !faceUp
    }

    fun printDetails(): String {
        if (faceUp) {
            return "$rank $suit"
        } else {
            return "-----"
        }
    }
}