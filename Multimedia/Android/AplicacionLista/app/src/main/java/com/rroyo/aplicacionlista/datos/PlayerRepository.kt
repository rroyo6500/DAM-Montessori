package com.rroyo.aplicacionlista.datos

import com.rroyo.aplicacionlista.modelo.Player

interface PlayerRepository {

    fun populate() {
        addNewPlayer(
            Player(
                "Arturo",
                "Caballero",
                "Azul"
            )
        )
        addNewPlayer(
            Player(
                "Elena",
                "Maga",
                "Rojo"
            )
        )
        addNewPlayer(
            Player(
                "Marcos",
                "Arquero",
                "Verde"
            )
        )
        addNewPlayer(
            Player(
                "Sofía",
                "Curandera",
                "Blanco"
            )
        )
        addNewPlayer(
            Player(
                "Diego",
                "Ladrón",
                "Negro"
            )
        )
        addNewPlayer(
            Player(
                "Lucía",
                "Mercadera",
                "Amarillo"
            )
        )
        addNewPlayer(
            Player(
                "Hugo",
                "Asesino",
                "Negro"
            )
        )
        addNewPlayer(
            Player(
                "Carmen",
                "Alquimista",
                "Morado"
            )
        )
        addNewPlayer(
            Player(
                "Pablo",
                "Domador",
                "Naranja"
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