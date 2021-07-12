package com.example.quizapp.Binding

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel(){

    //Live data of string
    val currentRandomFruitName:LiveData<String>
        get() = FakeRepository.currentRandomFruitName

    fun onChangeRandomFruitClick() = FakeRepository.changeCurrentRandomFruitName()

    @Bindable
    val editTextContent = MutableLiveData<String>();

    private val _displayEditTextContent = MutableLiveData<String>();

    val displayedEditTextContent:LiveData<String>
        get() = _displayEditTextContent;

    fun onDisplayEditTextContentClick() {
        _displayEditTextContent.postValue(editTextContent.value)
    }

    fun onSelectRandomEditTextFruit() {
        editTextContent.value = FakeRepository.getRandomFruitName()
    }

}