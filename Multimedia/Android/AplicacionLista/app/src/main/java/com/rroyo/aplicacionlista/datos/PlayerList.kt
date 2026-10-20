package com.rroyo.aplicacionlista.datos

import com.rroyo.aplicacionlista.modelo.Player

class PlayerList : PlayerRepository {

    private val list: MutableList<Player> = mutableListOf()

    override fun addNewPlayer(p: Player) {
        list.add(p)
    }

    override fun updatePlayer(id: Int, p: Player) {
        list[id] = p
    }

    override fun deletePlayer(id: Int) {
        list.removeAt(id)
    }

    override fun getPlayer(name: String): Player? =
        list.firstOrNull() { it.nombre == name }

    override fun getPlayer(id: Int): Player = list[id]

    override fun length(): Int = list.size



}