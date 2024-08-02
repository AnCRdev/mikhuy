package com.example.myappdecomida

import androidx.lifecycle.ViewModel
import com.example.myappdecomida.Repository.Dish

class SharedViewModel : ViewModel() {
    var cartItems: Array<Dish> = arrayOf()
}