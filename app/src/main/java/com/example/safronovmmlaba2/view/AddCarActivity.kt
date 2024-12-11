package com.example.safronovmmlaba2.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safronovmmlaba2.databinding.ActivityAddCarBinding
import com.example.safronovmmlaba2.model.Car
import com.example.safronovmmlaba2.utils.InputUtils

class AddCarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCarBinding // Инициализация View Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Установка View Binding
        binding = ActivityAddCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Обработка нажатия кнопки "Add Car"
        binding.addCarButton.setOnClickListener {
            val brand = binding.brandEditText.text.toString().trim()
            val year = InputUtils.parseInt(binding.yearEditText.text.toString(), defaultValue = 2023)
            val mileage = InputUtils.parseInt(binding.mileageEditText.text.toString())
            val maxSpeed = InputUtils.parseInt(binding.maxSpeedEditText.text.toString())

            if (brand.isEmpty() || year <= 0 || mileage < 0 || maxSpeed <= 0) {
                Toast.makeText(this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newCar = Car(
                id = (1..1000).random(),
                brand = brand,
                year = year,
                mileage = mileage,
                maxSpeed = maxSpeed
            )

            val resultIntent = Intent().apply {
                putExtra("NEW_CAR", newCar)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
