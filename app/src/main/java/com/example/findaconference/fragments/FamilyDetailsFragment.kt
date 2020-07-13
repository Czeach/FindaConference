package com.example.findaconference.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findaconference.R
import com.example.findaconference.databinding.FragmentFamilyDetailsBinding
import com.example.findaconference.databinding.FragmentLitigationDetailBinding

class FamilyDetailsFragment : Fragment() {

    private lateinit var binding: FragmentFamilyDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFamilyDetailsBinding.inflate(inflater)

        val family = FamilyDetailsFragmentArgs.fromBundle(requireArguments()).FamilyArgs

        binding.confName.text = family.name
        binding.confCode.text = "Conference Code: ${family.conference_code}"
        binding.confDate.text = family.date
        binding.confVenue.text = family.venue
        binding.confDesc.text = family.description
        binding.confRegFee.text = family.registration

        val image = context?.assets?.open(family.image)
        binding.image.setImageDrawable(Drawable.createFromStream(image, null))

        return binding.root
    }

}