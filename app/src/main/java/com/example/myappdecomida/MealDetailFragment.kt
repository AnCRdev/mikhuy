package com.example.myappdecomida

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.myappdecomida.comida.Meal
import com.squareup.picasso.Picasso

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MealDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MealDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_detail, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val meal = arguments?.getSerializable("meal") as Meal?

        view.findViewById<TextView>(R.id.meal_name).text = meal?.strMeal
        Picasso.get().load(meal?.strMealThumb).into(view.findViewById<ImageView>(R.id.meal_image))

        //boton que cierra el fragment
        view.findViewById<Button>(R.id.close_button).setOnClickListener {
            findNavController().navigate(R.id.action_mealDetailFragment_to_comida)
        }
    }
        companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MealDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}