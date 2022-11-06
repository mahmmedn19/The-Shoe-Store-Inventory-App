package com.example.shoestore.views.shoeDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeDetailsBinding
import com.example.shoestore.models.ShoeItem
import com.example.shoestore.viewmodel.ShoeListingViewModel

class ShoeDetailsFragment : Fragment() {
    private val shoesViewModel: ShoeListingViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentShoeDetailsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        binding.lifecycleOwner = this
        binding.shoeListingViewModel = shoesViewModel
        binding.shoe = ShoeItem()
        binding.btnSave.setOnClickListener {
            // add new details
            shoesViewModel.addShoes(shoesViewModel.shoe)
            val action = ShoeDetailsFragmentDirections.actionShoeDetailsToShoeListings()
            it.findNavController().navigate(action)
        }

        binding.btnCancel.setOnClickListener {
            val action = ShoeDetailsFragmentDirections.actionShoeDetailsToShoeListings()
            it.findNavController().navigate(action)
        }
        return binding.root
    }


}