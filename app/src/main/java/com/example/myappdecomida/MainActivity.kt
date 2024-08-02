package com.example.myappdecomida

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myappdecomida.actividades.ActivityRecover
import com.example.myappdecomida.actividades.ActivitySignUp
import com.example.myappdecomida.actividades.DBHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var emailTextView: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var dbh: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Variables del login
        loginButton = findViewById(R.id.login_button)
        emailTextView = findViewById(R.id.login_username)
        passwordEditText = findViewById(R.id.login_password_edit_text)

        dbh = DBHelper(this)

        // Función para ingresar al menú
        loginButton.setOnClickListener {
            val user = emailTextView.text.toString().trim()
            val pwd = passwordEditText.text.toString().trim()
            if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pwd)) {
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                try {
                    val checkuser = dbh.checkuserpass(user, pwd)
                    if (checkuser) {
                        Toast.makeText(this, "Ingreso exitoso", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, NavBar::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
        }
    }


    fun onRegisterLinkClicked(view: View) {
        // Código que se ejecutará cuando se haga clic en el TextView para registrarse
        val intent = Intent(this, ActivitySignUp::class.java)
        startActivity(intent)
    }

    fun onRecoverLinkClicked(view: View) {
        // Código que se ejecutará cuando se haga clic en el TextView para recuperar cuenta
        val intent = Intent(this, ActivityRecover::class.java)
        startActivity(intent)
    }
}