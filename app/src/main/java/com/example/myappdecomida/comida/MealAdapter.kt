package com.example.myappdecomida

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myappdecomida.comida.Meal
import com.squareup.picasso.Picasso
import kotlin.math.min

class MealAdapter(private val meals: List<Meal>) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {

    //ventana de inicio llamado al xml por cada uno
    class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imagen)
        val textName: TextView = view.findViewById(R.id.nombreTencia)
        val textCost: TextView = view.findViewById(R.id.text_cost_1)
    }

    //ventana de inicio llamado al xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tendenciasmenu, parent, false)
        return MealViewHolder(view)
    }

    //datos a mostrar en la ventana de inicio
    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        Picasso.get().load(meal.strMealThumb).into(holder.imageView)
        holder.textName.text = meal.strMeal
        holder.textCost.text = "$32.000" // Precio estático por ahora
    }


    //cantidad ventanas recomendadas en el inicio
    override fun getItemCount() = min(6, meals.size)
}