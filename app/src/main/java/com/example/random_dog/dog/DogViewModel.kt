package com.example.random_dog.dog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.random_dog.model.DogResponse
import com.example.random_dog.service.DogService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DogViewModel(private val dogService: DogService): ViewModel() {

    private val _showLoader = MutableLiveData<Boolean>()
    val showLoader: LiveData<Boolean>
        get() = _showLoader

    private val _showMessageError = MutableLiveData<Boolean>()
    val showMessageError: LiveData<Boolean>
        get() = _showMessageError

    private val _dog = MutableLiveData<DogResponse>()
    val dog: LiveData<DogResponse>
        get() = _dog

    fun getRandomDog() {
        _showLoader.postValue(true)

        dogService.getRandomDog().enqueue(object : Callback<DogResponse> {
            override fun onResponse(
                call: Call<DogResponse>?,
                response: Response<DogResponse>?
            ) {
                _showMessageError.postValue(response?.isSuccessful?.not())
                _showLoader.postValue(false)

                response?.takeIf { it.isSuccessful }?.run {
                    body()?.run {
                        _dog.postValue(this)
                    }
                }
            }

            override fun onFailure(call: Call<DogResponse>?, t: Throwable?) {
                _showMessageError.postValue(true)
                _showLoader.postValue(false)
            }
        })
    }

}