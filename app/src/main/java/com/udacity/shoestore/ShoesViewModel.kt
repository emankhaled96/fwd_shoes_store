package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoesViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoesList

    private val _addedShoe = MutableLiveData<Shoe?>()

    val addedShoeName = MutableLiveData<String?>()

    val addedShoeSize = MutableLiveData<String?>()

    val addedShoeDescription = MutableLiveData<String?>()

    val addedShoeCompany = MutableLiveData<String?>()

    val addedShoeImgLink = MutableLiveData<String?>()

    private val _shoeAdded = MutableLiveData<Boolean>()
    val shoeAdded: LiveData<Boolean>
        get() = _shoeAdded

    private val _cancelClicked = MutableLiveData<Boolean>()
    val cancelClicked: LiveData<Boolean>
        get() = _cancelClicked

    private val _formValidity = MutableLiveData<Boolean>()
    val formValidity: LiveData<Boolean>
        get() = _formValidity

    init {
        _formValidity.value = true
        _cancelClicked.value = false
        _shoeAdded.value = false
        setShoesList()
    }

    private fun setShoesList() {
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

    fun setBackShoeAdded(added: Boolean) {
        _shoeAdded.value = added
    }

    fun setBackCancel(cancelled: Boolean) {
        _cancelClicked.value = cancelled
    }
    fun validateForm(): Boolean {
        return !(addedShoeName.value == null ||
                addedShoeDescription.value == null ||
                addedShoeCompany.value == null ||
                addedShoeSize.value == null ||
                addedShoeImgLink.value == null)
    }

    fun cancel() {
        _cancelClicked.value = true
    }

    fun addShoe() {
        if(validateForm()){
            _formValidity.value = true
            _addedShoe.value = Shoe(
                addedShoeName.value!!,
                addedShoeSize.value?.toDouble()!!,
                addedShoeCompany.value!!,
                addedShoeDescription.value!!,
                listOf(addedShoeImgLink.value.toString())
            )
            _shoesList.value?.add(_addedShoe.value!!)
            _shoeAdded.value = true
            clear()
        }else{
            _formValidity.value = false
        }


    }

    private fun clear() {
        addedShoeName.value = null
        addedShoeCompany.value = null
        addedShoeSize.value = null
        addedShoeDescription.value = null
        addedShoeImgLink.value = null
        _addedShoe.value = null
    }

}