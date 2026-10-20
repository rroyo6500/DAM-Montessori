package com.rroyo.aplicacionlista.modelo

data class Player (
    val nombre: String,
    val ocupacion: String,
    val bando: String,
    var descripcion: String = "",
    var opinion: Int = 0
) {



}