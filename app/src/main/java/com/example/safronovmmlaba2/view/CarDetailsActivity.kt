package com.example.safronovmmlaba2.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safronovmmlaba2.databinding.ActivityCarDetailsBinding
import com.example.safronovmmlaba2.model.Car
import com.example.safronovmmlaba2.utils.InputUtils

class CarDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarDetailsBinding // Инициализация View Binding
    private var car: Car? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Установка View Binding
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получение данных автомобиля из Intent
        car = intent.getParcelableExtra("CAR")

        // Установить данные автомобиля в поля
        car?.let {
            binding.brandEditText.setText(it.brand)
            binding.yearEditText.setText(it.year.toString())
            binding.mileageEditText.setText(it.mileage.toString())
            binding.maxSpeedEditText.setText(it.maxSpeed.toString())
        }

        // Обработка нажатия кнопки "Save"
        binding.saveButton.setOnClickListener {
            val updatedCar = car?.copy(
                brand = binding.brandEditText.text.toString().trim(),
                year = InputUtils.parseInt(binding.yearEditText.text.toString()),
                mileage = InputUtils.parseInt(binding.mileageEditText.text.toString()),
                maxSpeed = InputUtils.parseInt(binding.maxSpeedEditText.text.toString())
            )

            if (updatedCar != null && updatedCar.brand.isNotEmpty() && updatedCar.year > 0 && updatedCar.mileage >= 0 && updatedCar.maxSpeed > 0) {
                val resultIntent = Intent().apply {
                    putExtra("UPDATED_CAR", updatedCar)
                }
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Please fill in all fields correctly.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
