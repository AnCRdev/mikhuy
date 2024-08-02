package com.example.myappdecomida

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
import androidx.versionedparcelable.VersionedParcelize
import com.example.myappdecomida.Repository.Dish
import com.example.myappdecomida.comida.Cart
import com.squareup.picasso.Picasso

class ConfirmarPedido : Fragment() {
    private lateinit var sharedViewModel: SharedViewModel
    private var cartItems : Array<Dish> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cartItems = it.getParcelableArray("cartItems") as Array<Dish>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmar_pedido, container, false)
    }

    data class Meal(
        val strMeal: String, // nombre de la comida
        val strMealThumb: String, // URL de la imagen de la comida
        val price: Double = (1..10).random().toDouble() // precio de la comida
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartLayout = view.findViewById<LinearLayout>(R.id.cart_layout)
        val layoutInflater = LayoutInflater.from(context)
        var total = 0.0

        val totalView = view.findViewById<TextView>(R.id.total_sum)

        for ((item, quantity) in Cart.items) {
            val productView = layoutInflater.inflate(R.layout.product_item, cartLayout, false)
            val incrementButton = productView.findViewById<Button>(R.id.increment_button)
            val decrementButton = productView.findViewById<Button>(R.id.decrement_button)
            val nameView = productView.findViewById<TextView>(R.id.product_name)
            nameView.text = item.strMeal

            val quantityView = productView.findViewById<TextView>(R.id.quantity_text)
            quantityView.text = quantity.toString()

            val imageView = productView.findViewById<ImageView>(R.id.product_image)
            Picasso.get().load(item.strMealThumb).into(imageView)

            val price = (1..10).random().toDouble() // Genera un precio aleatorio
            total += price * quantity

            val priceView = productView.findViewById<TextView>(R.id.product_price)
            priceView.text = "Precio: ${price * quantity}" // Muestra el precio en la vista

            incrementButton.setOnClickListener {
                if (Cart.items[item]!! < 5) {
                    Cart.items[item] = Cart.items[item]!! + 1
                    quantityView.text = Cart.items[item].toString()
                    total += price
                    priceView.text = "Precio: ${price * Cart.items[item]!!}" // Actualiza el precio en la vista
                    totalView.text = "Total: $total" // Actualiza el total general
                }
            }
            decrementButton.setOnClickListener {
                if (Cart.items[item]!! > 1) {
                    Cart.items[item] = Cart.items[item]!! - 1
                    quantityView.text = Cart.items[item].toString()
                    total -= price
                    priceView.text = "Precio: ${price * Cart.items[item]!!}" // Actualiza el precio en la vista
                    totalView.text = "Total: $total" // Actualiza el total general
                }
            }
            val removeButton = productView.findViewById<Button>(R.id.delete_button)
            removeButton.setOnClickListener {
                total -= price * Cart.items[item]!!
                Cart.items.remove(item)
                cartLayout.removeView(productView)
                totalView.text = "Total: $total" // Actualiza el total general
            }
            cartLayout.addView(productView)

            // Imprimir los datos en la consola de depuración
            Log.d("CarritoCompras", "Nombre del producto: ${item.strMeal}, Cantidad: ${Cart.items[item]}, Precio: ${price * Cart.items[item]!!}")
        }

        totalView.text = "Total: $total"

        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener {
            val action = R.id.action_confirmarPedido_to_pago

            // Crear un Bundle y poner los datos que quieres enviar
            val bundle = Bundle()
            val productNames = Cart.items.keys.map { it.strMeal }.toTypedArray()
            val productQuantities = Cart.items.values.toIntArray()
            bundle.putStringArray("productNames", productNames)
            bundle.putIntArray("productQuantities", productQuantities)
            bundle.putDouble("total", total)

            // Navegar al fragmento de pago y pasar el Bundle como argumento
            findNavController().navigate(action, bundle)
        }

        val back_carrito_compras = view.findViewById<ImageView>(R.id.back_carrito_compras)
        back_carrito_compras.setOnClickListener {
            val action = R.id.action_confirmarPedido_to_carritoCompras
            findNavController().navigate(action)
        }

    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ConfirmarPedido().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }

            }}}