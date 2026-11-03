package com.rroyo.aplicacionlista

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PlayerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val playerId = intent.extras?.getInt("player_id") ?: 0
        enableEdgeToEdge()
        setContentView(R.layout.activity_player)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val player = MainActivity.playerList.getPlayer(playerId)

        findViewById<TextView>(R.id.tNombre).text = player.nombre
        findViewById<TextView>(R.id.tOcupacion).text = player.ocupacion
        findViewById<TextView>(R.id.tDescripcion).text = player.descripcion
    }
}