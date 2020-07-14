package com.example.findaconference.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findaconference.databinding.FragmentLitigationDetailBinding

class LitigationDetailFragment : Fragment() {

    private lateinit var binding: FragmentLitigationDetailBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLitigationDetailBinding.inflate(inflater)

        val litigation = LitigationDetailFragmentArgs.fromBundle(requireArguments()).LitigationArgs

        binding.confName.text = litigation.name
        binding.confCode.text = "Conference Code: ${litigation.conference_code}"
        binding.confDate.text = litigation.date
        binding.confVenue.text = litigation.venue
        binding.confDesc.text = litigation.description
        binding.confRegFee.text = litigation.registration

        val image = context?.assets?.open(litigation.image)
        binding.image.setImageDrawable(Drawable.createFromStream(image, null))

        return binding.root
    }

}