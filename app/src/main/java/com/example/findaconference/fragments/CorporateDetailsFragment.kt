package com.example.findaconference.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findaconference.R
import com.example.findaconference.databinding.FragmentCorporateDetailsBinding
import com.example.findaconference.databinding.FragmentFamilyDetailsBinding

class CorporateDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCorporateDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCorporateDetailsBinding.inflate(inflater)

        val corporate = CorporateDetailsFragmentArgs.fromBundle(requireArguments()).CorporateArgs

        binding.confName.text = corporate.name
        binding.confCode.text = "Conference Code: ${corporate.conference_code}"
        binding.confDate.text = corporate.date
        binding.confVenue.text = corporate.venue
        binding.confDesc.text = corporate.description
        binding.confRegFee.text = corporate.registration

        val image = context?.assets?.open(corporate.image)
        binding.image.setImageDrawable(Drawable.createFromStream(image, null))

        return binding.root
    }

}