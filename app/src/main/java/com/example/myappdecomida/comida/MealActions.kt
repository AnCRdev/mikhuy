package com.example.myappdecomida

import android.widget.Button
import android.widget.ImageView
import com.example.myappdecomida.comida.Cart
import com.example.myappdecomida.comida.Meal
import com.squareup.picasso.Picasso

class MealActions {
    fun handleHeartButton(heartButton: Button, meal: Meal) {
        heartButton.setOnClickListener {
            if (FavoritosData.items.contains(meal)) {
                FavoritosData.items.remove(meal)
                heartButton.setBackgroundResource(R.drawable.favoritehome) // Cambia a un corazón vacío
            } else {
                FavoritosData.items.add(meal)
                heartButton.setBackgroundResource(R.drawable.ic_favoritorelleno) // Cambia a un corazón lleno
            }
        }
    }

    fun handleAddButton(addButton: Button, imageView: ImageView, meal: Meal) {
        addButton.setOnClickListener {
            val quantity = Cart.items[meal] ?: 0
            Cart.items[meal] = quantity + 1
            Picasso.get().load(meal.strMealThumb).into(imageView) // Aquí se carga la imagen
        }
    }
}