package com.example.proyecto201apirest5f2023

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView

class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        val card1=findViewById<CardView>(R.id.cardView)

        card1.setOnClickListener {
            Toast.makeText(
                this,
                "Selecciono clientes!!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}