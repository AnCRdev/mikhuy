package com.example.myappdecomida.pedidos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.myappdecomida.R
import com.example.myappdecomida.databinding.FragmentPedidosBinding

class Pedidos : Fragment() {

    private var _binding: FragmentPedidosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPedidosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val pedidosActivoFragment = pedidosActivo()
        val pedidosListaFragment = pedidosLista()
        val pedidosCanceladoFragment = PedidosCencelado()

        // Muestra el primer fragmento por defecto
        replaceFragment(pedidosActivoFragment)
        changeButtonColor(binding.buttonActiva, binding.buttonLista, binding.buttonCancelada)

        // Configura los oyentes de clic para los botones
        binding.buttonActiva.setOnClickListener {
            replaceFragment(pedidosActivoFragment)
            changeButtonColor(binding.buttonActiva, binding.buttonLista, binding.buttonCancelada)
        }

        binding.buttonLista.setOnClickListener {
            replaceFragment(pedidosListaFragment)
            changeButtonColor(binding.buttonLista, binding.buttonActiva, binding.buttonCancelada)
        }

        binding.buttonCancelada.setOnClickListener {
            replaceFragment(pedidosCanceladoFragment)
            changeButtonColor(binding.buttonCancelada, binding.buttonActiva, binding.buttonLista)
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = childFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layoutOrdenes, fragment)
        fragmentTransaction.commit()
    }

    private fun changeButtonColor(selected: Button, vararg others: Button) {
        val selectedColor = ContextCompat.getColor(requireContext(), R.color.botonesColor)
        val defaultColor = ContextCompat.getColor(requireContext(), R.color.botonColorSegundo)

        selected.setBackgroundColor(selectedColor)
        others.forEach { it.setBackgroundColor(defaultColor) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}