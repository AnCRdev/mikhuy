package com.example.myappdecomida

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myappdecomida.comida.Meal
import com.squareup.picasso.Picasso
import kotlin.math.min

class MealAdapterComida(private val meals: List<Meal>, private val mealActions: MealActions) : RecyclerView.Adapter<MealAdapterComida.MealViewHolder>() {

    class MealViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.meal_image)
        val addButton: Button = view.findViewById(R.id.add_button)
        val heartButton: Button = view.findViewById(R.id.heart_button)
        val mealName: TextView = view.findViewById(R.id.meal_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.meal_item, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = meals[position]
        Picasso.get().load(meal.strMealThumb).into(holder.imageView)
        holder.mealName.text = meal.strMeal

        holder.imageView.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("meal", meal)
            }
            it.findNavController().navigate(R.id.mealDetailFragment, bundle)
        }

        // Verificar si la comida está en la lista de favoritos
        if (FavoritosData.items.contains(meal)) {
            holder.heartButton.setBackgroundResource(R.drawable.ic_favoritorelleno) // Cambia a un corazón lleno
        } else {
            holder.heartButton.setBackgroundResource(R.drawable.favoritehome) // Cambia a un corazón vacío
        }

        mealActions.handleHeartButton(holder.heartButton, meal)
        mealActions.handleAddButton(holder.addButton, holder.imageView, meal)
    }

    override fun getItemCount() = min(meals.size, 10)
}