package com.example.myappdecomida

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myappdecomida.Repository.Dish
import com.squareup.picasso.Picasso
import com.example.myappdecomida.comida.Cart
import com.google.android.material.bottomnavigation.BottomNavigationView

class CarritoCompras : Fragment() {

    private val cart = OrderCart()
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_carrito_compras, container, false)
        return inflater.inflate(R.layout.fragment_confirmacion, container, false)
    }

    private fun getCartItems() : List<Dish>{
        return cart.getItems()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.GONE

        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener {
            val cartItems = getCartItems()
            val bundle = Bundle().apply {
                putParcelableArray("cartItems", cartItems.toTypedArray())
            }
            findNavController().navigate(R.id.action_carritoCompras_to_confirmarPedido, bundle)
        }

        val backButton = view.findViewById<ImageView>(R.id.back_confirmar_compra)
        backButton.setOnClickListener {
            val intent = Intent(activity, NavBar::class.java)
            startActivity(intent)
        }

        val cartLayout = view.findViewById<LinearLayout>(R.id.cart_layout)
        val layoutInflater = LayoutInflater.from(context)

        for ((item, quantity) in Cart.items) {
            val productView = layoutInflater.inflate(R.layout.cart_item, cartLayout, false)

            val nameView = productView.findViewById<TextView>(R.id.cart_item_name)
            nameView.text = item.strMeal

            val quantityView = productView.findViewById<TextView>(R.id.cart_item_quantity)
            quantityView.text = quantity.toString()

            val imageView = productView.findViewById<ImageView>(R.id.cart_item_image)
            Picasso.get().load(item.strMealThumb).into(imageView) // Aquí se carga la imagen del producto

            val removeButton = productView.findViewById<Button>(R.id.remove_button)
            removeButton.setOnClickListener {
                Cart.items.remove(item)
                cartLayout.removeView(productView)
            }
            cartLayout.addView(productView)

            // Imprimir los datos en la consola de depuración
            Log.d("CarritoCompras", "Nombre del producto: ${item.strMeal}, Cantidad: $quantity")
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        super.onDestroyView()

        val navBar = activity?.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navBar?.visibility = View.VISIBLE

    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarritoCompras().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}