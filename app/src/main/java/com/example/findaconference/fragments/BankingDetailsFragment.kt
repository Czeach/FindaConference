package com.example.findaconference.fragments

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.findaconference.databinding.FragmentBankingDetailsBinding

class BankingDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBankingDetailsBinding

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBankingDetailsBinding.inflate(inflater)

        val banking = BankingDetailsFragmentArgs.fromBundle(requireArguments()).BankingArgs

        binding.confName.text = banking.name
        binding.confCode.text = "Conference Code: ${banking.conference_code}"
        binding.confDate.text = banking.date
        binding.confVenue.text = banking.venue
        binding.confDesc.text = banking.description
        binding.confRegFee.text = banking.registration

        val image = context?.assets?.open(banking.image)
        binding.image.setImageDrawable(Drawable.createFromStream(image, null))

        return binding.root
    }

}