package com.example.random_dog.service

import com.example.random_dog.model.DogResponse
import retrofit2.Call
import retrofit2.http.GET

interface DogService {

    @GET("breeds/image/random")
    fun getRandomDog(): Call<DogResponse>

}