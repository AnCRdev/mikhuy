package com.example.myappdecomida.actividades

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.myappdecomida.MainActivity
import com.example.myappdecomida.R

class ActivityRecover : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover)
    }

    fun onLoginLink2Clicked(view: View) {
        // Código que se ejecutará cuando se haga clic en el TextView para iniciar sesión
        val intent = Intent(this,  MainActivity::class.java)
        startActivity(intent)
    }
    
}