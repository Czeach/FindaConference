package com.example.findaconference.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.findaconference.FavouritesViewModel
import com.example.findaconference.adapters.FavouritesAdapter
import com.example.findaconference.databinding.FragmentFavouritesBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import javax.xml.transform.ErrorListener


class FavouritesFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesBinding
    private lateinit var favouritesViewModel: FavouritesViewModel
    private lateinit var favouritesRecycler: RecyclerView

    private val favouritesAdapter = FavouritesAdapter(requireContext())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFavouritesBinding.inflate(inflater)

        favouritesViewModel = ViewModelProvider(this).get(FavouritesViewModel::class.java)

        favouritesRecycler = binding.favouritesList.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = favouritesAdapter
        }

        favouritesViewModel.favourites?.observe(viewLifecycleOwner, Observer {
            it?.let {

            }
        })

        return binding.root
    }
}