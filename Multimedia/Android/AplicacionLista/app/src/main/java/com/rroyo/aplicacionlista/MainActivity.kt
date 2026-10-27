package com.rroyo.aplicacionlista

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.rroyo.aplicacionlista.datos.PlayerList

class MainActivity : AppCompatActivity() {

    companion object {
        val playerList = PlayerList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        playerList.populate()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.btnPersonaje).setOnClickListener {
            selectDialog()
        }

    }

    fun selectDialog() {
        val entrada = EditText(this)
        entrada.inputType = InputType.TYPE_CLASS_NUMBER
        entrada.hint = "Numero de personaje"
        AlertDialog.Builder(this)
            .setTitle("Elige un Personaje")
            .setMessage("Introduce un numero para elegir al personaje")
            .setView(entrada)
            .setPositiveButton("Buscar") { p0, p1 ->
                val num = entrada.text.toString().toInt()
                val intent = Intent(this, PlayerActivity::class.java)
                intent.putExtra("player_id", num)
                startActivity(intent)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

}