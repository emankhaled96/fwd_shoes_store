package com.udacity.shoestore

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoesViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    private val _addedShoe = MutableLiveData<Shoe>()
    val addedShoe: LiveData<Shoe>
        get() = _addedShoe

    private val _addedShoeName = MutableLiveData<String?>()
    val addedShoeName: LiveData<String?>
        get() = _addedShoeName

    private val _addedShoeSize = MutableLiveData<Double?>()
    val addedShoeSize: LiveData<Double?>
        get() = _addedShoeSize

    private val _addedShoeDescription = MutableLiveData<String?>()
    val addedShoeDescription: LiveData<String?>
        get() = _addedShoeDescription

    private val _addedShoeCompany = MutableLiveData<String?>()
    val addedShoeCompany: LiveData<String?>
        get() = _addedShoeCompany

    private val _addedShoeImgLink = MutableLiveData<String?>()
    val addedShoeImgLink: LiveData<String?>
        get() = _addedShoeImgLink

    init {
        setShoesList()
    }

    fun setShoesList() {
        _shoesList.value = listOf(
            Shoe(
                "Sport Shoes",
                38.0,
                "Adidas",
                "a comfortable Nice Shoes",
                listOf("https://m.media-amazon.com/images/I/61XSOY-ovbL._AC_SX575_.jpg")
            ),
            Shoe(
                "Classic Shoes",
                40.0,
                "Nike",
                "Classic Comfortable Shoes",
                listOf("https://m.media-amazon.com/images/I/51zDSpJ1ifL._AC_SY675_.jpg")
            )

        ).toMutableList()
    }

    fun setShoeName(name: String) {
        Timber.tag("name").i(name)
        _addedShoeName.value = name
    }

    fun setShoeCompany(companyName: String) {
        Timber.tag("companyName").i(companyName)

        _addedShoeCompany.value = companyName

    }

    fun setShoeSize(size: Double) {
        Timber.tag("size").i(size.toString())
        _addedShoeSize.value = size
    }

    fun setShoeDescription(description: String) {
        Timber.tag("description").i(description)

        _addedShoeDescription.value = description
    }

    fun setShoeImgLink(imgLink: String) {
        Timber.tag("imgLink").i(imgLink)

        _addedShoeImgLink.value = imgLink
    }

    fun validateForm(): Boolean {
        return !(_addedShoeName.value == null ||
                _addedShoeDescription.value == null ||
                _addedShoeCompany.value == null ||
                _addedShoeSize.value == null ||
                _addedShoeImgLink.value == null)
    }

    fun addShoe() {
        _addedShoe.value = Shoe(
            _addedShoeName.value!!,
            _addedShoeSize.value!!,
            _addedShoeCompany.value!!,
            _addedShoeDescription.value!!,
            listOf(_addedShoeImgLink.value!!)
        )
        _shoesList.value?.add(_addedShoe.value!!)
        _addedShoeName.value = null
        _addedShoeCompany.value = null
        _addedShoeSize.value = null
        _addedShoeDescription.value = null
        _addedShoeImgLink.value = null
    }

}