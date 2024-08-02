import android.content.Context
import android.content.SharedPreferences
import com.example.myappdecomida.comida.Meal
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SharedPreference(val context: Context) {
    private val PREFS_NAME = "meal_app"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveFavorites(list: Set<Meal>) {
        val editor = sharedPref.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString("favorite_list", json)
        editor.apply()
    }

    fun loadFavorites(): MutableSet<Meal> {
        val gson = Gson()
        val json = sharedPref.getString("favorite_list", null)
        val type = object : TypeToken<Set<Meal>>() {}.type
        return gson.fromJson(json, type) ?: mutableSetOf()
    }
}