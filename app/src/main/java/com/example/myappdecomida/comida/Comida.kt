package com.example.myappdecomida.comida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.myappdecomida.ApiService
import com.example.myappdecomida.FavoritosData
import com.example.myappdecomida.MealActions
import com.example.myappdecomida.MealAdapter
import com.example.myappdecomida.MealAdapterComida
import com.example.myappdecomida.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import kotlin.math.min
import java.io.Serializable

data class Meal(
    val strMeal: String, // nombre de la comida
    val strMealThumb: String // URL de la imagen de la comida
) : Serializable

object Cart {
    val items = mutableMapOf<Meal, Int>()
}

class Comida : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comida, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            val apiService = ApiService()

            try {
                val meals = apiService.fetchMeals()

                withContext(Dispatchers.Main) {
                    val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
                    val mealActions = MealActions()
                    recyclerView.adapter = MealAdapterComida(meals, mealActions)
                }
            } catch (e: IOException) {
                // Manejar la excepción
            }
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Comida().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}