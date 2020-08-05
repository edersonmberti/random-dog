package com.example.random_dog.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.random_dog.service.DogService

class DogViewModelFactory(private val dogService: DogService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(DogService::class.java)
            .newInstance(dogService)
    }

}