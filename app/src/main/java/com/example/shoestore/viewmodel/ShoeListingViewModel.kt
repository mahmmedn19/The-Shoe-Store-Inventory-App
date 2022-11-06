package com.example.shoestore.viewmodel

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoestore.BR
import com.example.shoestore.models.ShoeItem

class ShoeListingViewModel : ViewModel(), Observable {
    private val propertyChangeRegistry = PropertyChangeRegistry()

    private val _shoes = MutableLiveData<MutableList<ShoeItem>>(mutableListOf())
    fun getShoeLiveData(): LiveData<MutableList<ShoeItem>> = _shoes



   /* fun addShoes(name: String, size: String, company: String, details: String) {
        _shoes.value?.add(ShoeItem(name, size, company, details))
    }*/
    fun addShoes(item: ShoeItem?) {
        item?.let {
            _shoes.value?.add(item)
        }
    }

    @Bindable
    var shoe = ShoeItem()
        set(value) {
            if (value != field) {
                field = value
                propertyChangeRegistry.notifyChange(this, BR.shoe)
            }
        }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        propertyChangeRegistry.remove(callback)
    }

}