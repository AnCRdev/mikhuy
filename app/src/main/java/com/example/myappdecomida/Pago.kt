package com.example.myappdecomida

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Pago : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pago, container, false)
    }
    class ProductAdapter(private val productList: List<String>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(android.R.id.text1)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false)
            return ProductViewHolder(view)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            holder.textView.text = productList[position]
        }

        override fun getItemCount() = productList.size
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Obtener los datos del Bundle
        val productNames = arguments?.getStringArray("productNames")
        val productQuantities = arguments?.getIntArray("productQuantities")
        val total = arguments?.getDouble("total")

        // Crear una lista de strings que representan los productos y sus cantidades
        val productsList = productNames?.mapIndexed { index, productName ->
            val productQuantity = productQuantities?.get(index)
            "$productName: $productQuantity"
        } ?: listOf()

        // Actualizar el RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.productoPago)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ProductAdapter(productsList)

        view.findViewById<TextView>(R.id.totalPago).text = "Total: $total"
        val orderButton = view.findViewById<Button>(R.id.order_button)
        orderButton.setOnClickListener {
            findNavController().navigate(R.id.action_pago_to_confirmacion)
        }
        val backButton = view.findViewById<ImageView>(R.id.back_confirmar_compra)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_pago_to_confirmarPedido)
        }
        
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Pago().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}