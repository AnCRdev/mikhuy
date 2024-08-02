package com.example.myappdecomida

import com.example.myappdecomida.Repository.Dish

class OrderCart {
    private val items = mutableListOf<Dish>()
    fun addItem(dish: Dish) {
        items.add(dish)
    }
    fun removeItem(dish: Dish) {
        items.remove(dish)
    }
    fun getItems(): List<Dish> {
        return items
    }
}