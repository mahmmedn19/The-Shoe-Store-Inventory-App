package com.example.shoestore.views.shoeListing

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.shoestore.R
import com.example.shoestore.databinding.FragmentShoeListingBinding
import com.example.shoestore.models.ShoeItem
import com.example.shoestore.screens.shoe_listings.ShoeLayout
import com.example.shoestore.viewmodel.ShoeListingViewModel


class ShoeListingFragment : Fragment() {

    private  val shoeListingsViewModel: ShoeListingViewModel by activityViewModels()
    private lateinit var binding: FragmentShoeListingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_listing, container, false)
        binding.lifecycleOwner = this
        shoeListingsViewModel.getShoeLiveData().observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                createShoes(it)
            }
        }
        binding.btnAddItem.setOnClickListener { view: View ->
            val action =
                ShoeListingFragmentDirections.actionShoeListingFragmentToShoeDetailsFragment()
            view.findNavController().navigate(action)
        }
        return binding.root
    }

    private fun createShoes(shoesList: List<ShoeItem>) {
        context?.let { context ->
            val shoeContainer = binding.addNewItem
            shoesList.forEach { shoe ->
                val shoeLayout = ShoeLayout(context)
                shoeLayout.loadShoe(shoe)
                shoeContainer.addView(shoeLayout)
                Log.i("ShoeListingFragment",shoe.name)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // The usage of an interface lets you inject your own implementation
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.logout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return NavigationUI.onNavDestinationSelected(
                    menuItem,
                    requireView().findNavController()
                )
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
         super.onCreateOptionsMenu(menu, inflater)
     inflater?.inflate(R.menu.logout_menu,menu)
     }

     override fun onOptionsItemSelected(item: MenuItem): Boolean {
         return NavigationUI.onNavDestinationSelected(item!!,requireView().findNavController())||
                 super.onOptionsItemSelected(item)
     }*/

}
