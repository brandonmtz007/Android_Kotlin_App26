package com.example.proyecto201apirest5f2023

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Registrarme : AppCompatActivity() {
    data class Resultados(
        val status:String
    )

    interface usuariosDBService{
        @GET("agregara.php")
        fun agregarUsuario(@Query("nombre") nombre: String, @Query("usuario") usuario: String, @Query("contrasena") contrasena: String): Call<Resultados>
    }

    object UsuariosDBClient {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://programacion-de-moviles.000webhostapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service=retrofit.create(usuariosDBService::class.java)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#E91E63"));
        setContentView(R.layout.activity_registrarme)
        val button3=findViewById<Button>(R.id.button3)
        val nombreCompleto=findViewById<EditText>(R.id.nombreCompleto)
        val usuario=findViewById<EditText>(R.id.usuario)
        val contrasena=findViewById<EditText>(R.id.contrasena)

        button3.setOnClickListener(View.OnClickListener { view ->
            CoroutineScope(Dispatchers.IO).launch {
                val resultado = UsuariosDBClient.service.agregarUsuario(nombreCompleto.text.toString(),
                    usuario.text.toString(),contrasena.text.toString())
                val body = resultado.execute().body()

                if (body != null) {
                    runOnUiThread {
                        if(body.status=="ok") {
                            Toast.makeText(view.context,"Registro agregado con existo!!",Toast.LENGTH_LONG).show()
                            finish();
                        }else
                        {
                            Toast.makeText(view.context,"No se pudo agregar el registro!!",Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }

        })

    }
}