package com.example.aston_intensiv_5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModel : ViewModel() {

    private val firstNameMutableLiveData = MutableLiveData("")
    val firstNameLiveData: LiveData<String> = firstNameMutableLiveData

    private val lastNameMutableLiveData = MutableLiveData("")
    val lastNameLiveData: LiveData<String> = lastNameMutableLiveData

    private val phoneMutableLiveData = MutableLiveData("")
    val phoneLiveData: LiveData<String> = phoneMutableLiveData

    fun setFirstName(firstName: String) {
        firstNameMutableLiveData.value = firstName
    }

    fun setLastName(lastName: String) {
        lastNameMutableLiveData.value = lastName
    }

    fun setPhone(phone: String) {
        phoneMutableLiveData.value = phone
    }

    private val clickMutableLiveData = MutableLiveData(0)
    val clickLiveData: LiveData<Int> = clickMutableLiveData

    fun setClick(click: Int?) {
            clickMutableLiveData.value = click
    }
}