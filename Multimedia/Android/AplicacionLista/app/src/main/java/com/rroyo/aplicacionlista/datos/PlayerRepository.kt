package com.rroyo.aplicacionlista.datos

import com.rroyo.aplicacionlista.modelo.Player

interface PlayerRepository {

    fun populate() {
        addNewPlayer(
            Player(
                "Player1",
                "Herrero",
                "Red"
            )
        )
    }

    fun addNewPlayer(p: Player)
    fun updatePlayer(id: Int, p: Player)
    fun deletePlayer(id: Int)
    fun getPlayer(name: String): Player?
    fun getPlayer(id: Int): Player
    fun length(): Int

}