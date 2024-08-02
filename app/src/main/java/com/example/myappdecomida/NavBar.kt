package com.example.myappdecomida

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.myappdecomida.Home
import com.example.myappdecomida.comida.Comida
import com.example.myappdecomida.databinding.ActivityNavBarBinding
import com.example.myappdecomida.pedidos.Pedidos

class NavBar : AppCompatActivity() {

    private lateinit var binding : ActivityNavBarBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavBarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        replaceFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {

            when(it.itemId){

                R.id.casa -> replaceFragment(Home())
                R.id.comida -> replaceFragment(Comida())
                R.id.favoritos -> replaceFragment(Favoritos())
                R.id.list -> replaceFragment(Pedidos())
                R.id.perfil -> replaceFragment(Usuario())

                else -> { }

            }

            true

        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
    }


}