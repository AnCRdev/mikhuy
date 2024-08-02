package com.example.myappdecomida.actividades

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myappdecomida.MainActivity
import com.example.myappdecomida.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.regex.Pattern

class ActivitySignUp : AppCompatActivity(){

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signupbutton: Button
    private lateinit var names: EditText
    private lateinit var email: EditText
    private lateinit var uuser: EditText
    private lateinit var spassword2: EditText
    private lateinit var spassword: EditText
    private lateinit var db: DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        //Variables del signup
        names  = findViewById(R.id.signup_name)
        email  = findViewById(R.id.signup_email)
        uuser  = findViewById(R.id.signup_username)
        spassword = findViewById(R.id.signup_password)
        spassword2 = findViewById(R.id.signup_password2)
        signupbutton  = findViewById(R.id.signup_button)

        db = DBHelper(this)

        signupbutton.setOnClickListener {
            val user =email .text.toString().trim()
            val pwd = spassword.text.toString().trim()
            val cpwd = spassword2.text.toString().trim()
            val savedata = db.insertData(user, pwd)

            if (TextUtils.isEmpty(user)|| TextUtils.isEmpty(pwd) || TextUtils.isEmpty(cpwd)){
                Toast.makeText(this, "Por favor llena todos los campos", Toast.LENGTH_SHORT).show()}
            else{
                if(pwd.equals(cpwd)){
                    if(savedata == true){
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this, "Error al registrar", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    fun isValidPassword(password: String): Boolean {
        val pattern = Pattern.compile("^(?=.[A-Z])(?=.\\d)(?=.*[!\\?])[A-Za-z\\d!\\?]{1,8}\$")
        val matcher = pattern.matcher(password)
        return matcher.matches()
    }

    private fun createAccount(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    Toast.makeText(baseContext, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Error")
                    builder.setMessage("Ocurrió el siguiente error: ${task.exception?.message}")
                    builder.setPositiveButton("OK", null)
                    val dialog = builder.create()
                    dialog.show()
                }
            }
    }

    fun onLoginLinkClicked(view: View) {
        // Código que se ejecutará cuando se haga clic en el TextView para iniciar sesión
        val intent = Intent(this,  MainActivity::class.java)
        startActivity(intent)
    }
}