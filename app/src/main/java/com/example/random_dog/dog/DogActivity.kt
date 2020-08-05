package com.example.random_dog.dog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.random_dog.R
import com.example.random_dog.model.DogResponse
import com.example.random_dog.service.RetrofitClient
import com.example.random_dog.util.DogViewModelFactory
import kotlinx.android.synthetic.main.activity_dog.*

class DogActivity : AppCompatActivity() {

    private lateinit var dogViewModel: DogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog)

        dogViewModel = ViewModelProvider(
            this,
            DogViewModelFactory(RetrofitClient.getDogService())
        ).get(DogViewModel::class.java)

        initiateObservers()

        dogViewModel.getRandomDog()

        button.setOnClickListener {
            dogViewModel.getRandomDog()
        }
    }

    private fun initiateObservers() {
        dogViewModel.run {
            dog.observe(this@DogActivity, Observer { setDogImage(it) })
        }
    }

    private fun setDogImage(dogResponse: DogResponse) {
        dogResponse.run {
            Glide.with(this@DogActivity)
                .load(imageUrl)
                .placeholder(R.color.cello)
                .error(ColorDrawable(Color.RED))
                .into(image)
        }
    }
}