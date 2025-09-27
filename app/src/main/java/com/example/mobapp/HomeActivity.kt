package com.example.mobapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val location: Location,
    val image: String
)

data class Location(
    val name: String
)

data class CharacterResponse(
    val results: List<Character>
)


interface RickAndMortyApi {
    @GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>
}


class CharacterAdapter(private val characters: List<Character>) :
    RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_character_name)
        val status: TextView = view.findViewById(R.id.tv_character_status)
        val location: TextView = view.findViewById(R.id.tv_character_location)
        val image: ImageView = view.findViewById(R.id.iv_character_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_character, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.name.text = character.name
        holder.status.text = "Status: ${character.status} - Species: ${character.species}"
        holder.location.text = "Location: ${character.location.name}"

        Glide.with(holder.image.context)
            .load(character.image)
            .placeholder(R.drawable.ic_person)
            .error(R.drawable.ic_person)
            .into(holder.image)
    }

    override fun getItemCount() = characters.size
}


class HomeActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DEBUG", "HomeActivity created")
        setContentView(R.layout.activity_home)

        recyclerView = findViewById(R.id.recyclerViewCharacters)
        progressBar = findViewById(R.id.progressBar)
        tvError = findViewById(R.id.tv_error)

        recyclerView.layoutManager = LinearLayoutManager(this)

        loadCharacters()
    }

    private fun loadCharacters() {
        progressBar.visibility = View.VISIBLE
        tvError.visibility = View.GONE

        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(RickAndMortyApi::class.java)

        MainScope().launch {
            try {
                val response = api.getCharacters()
                if (response.isSuccessful) {
                    val characters = response.body()?.results ?: emptyList()
                    recyclerView.adapter = CharacterAdapter(characters)
                    progressBar.visibility = View.GONE
                } else {
                    showError()
                }
            } catch (e: Exception) {
                showError()
            }
        }
    }

    private fun showError() {
        progressBar.visibility = View.GONE
        tvError.visibility = View.VISIBLE
    }
}
