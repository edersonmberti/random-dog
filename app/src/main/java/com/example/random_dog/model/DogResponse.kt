package com.example.random_dog.model

import com.google.gson.annotations.SerializedName

data class DogResponse (
    @SerializedName("message")
    val imageUrl: String,
    val status: String
)

