package com.example.findaconference.fragments

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Build.VERSION_CODES.P
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.findaconference.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val imageView: ImageView = binding.image

        val istr = this.context?.assets?.open("images/tokyo.jpg")
        imageView.setImageDrawable(Drawable.createFromStream(istr, null))

        return binding.root
    }

}