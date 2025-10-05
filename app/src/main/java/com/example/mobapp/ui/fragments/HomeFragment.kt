package com.example.mobapp.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobapp.CharacterAdapter
import com.example.mobapp.CharacterResponse
import com.example.mobapp.databinding.FragmentHomeBinding
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

interface RickAndMortyApi {
    @retrofit2.http.GET("character")
    suspend fun getCharacters(): Response<CharacterResponse>
}

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("DEBUG", "HomeFragment created")
        binding.recyclerViewCharacters.layoutManager = LinearLayoutManager(requireContext())

        loadCharacters()
    }

    private fun loadCharacters() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvError.visibility = View.GONE

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
                    binding.recyclerViewCharacters.adapter = CharacterAdapter(characters)
                    binding.progressBar.visibility = View.GONE
                } else {
                    showError()
                }
            } catch (e: Exception) {
                showError()
            }
        }
    }

    private fun showError() {
        binding.progressBar.visibility = View.GONE
        binding.tvError.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}