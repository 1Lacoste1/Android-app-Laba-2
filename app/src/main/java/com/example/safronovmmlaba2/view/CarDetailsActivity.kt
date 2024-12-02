package com.example.safronovmmlaba2.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.safronovmmlaba2.R
import com.example.safronovmmlaba2.model.Car
import com.example.safronovmmlaba2.utils.InputUtils

class CarDetailsActivity : AppCompatActivity() {
    private var car: Car? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_details)

        car = intent.getParcelableExtra("CAR")

        // Установить данные автомобиля в поля
        car?.let {
            findViewById<EditText>(R.id.brandEditText).setText(it.brand)
            findViewById<EditText>(R.id.yearEditText).setText(it.year.toString())
            findViewById<EditText>(R.id.mileageEditText).setText(it.mileage.toString())
            findViewById<EditText>(R.id.maxSpeedEditText).setText(it.maxSpeed.toString())
        }

        findViewById<Button>(R.id.saveButton).setOnClickListener {
            val updatedCar = car?.copy(
                brand = findViewById<EditText>(R.id.brandEditText).text.toString().trim(),
                year = InputUtils.parseInt(findViewById<EditText>(R.id.yearEditText).text.toString()),
                mileage = InputUtils.parseInt(findViewById<EditText>(R.id.mileageEditText).text.toString()),
                maxSpeed = InputUtils.parseInt(findViewById<EditText>(R.id.maxSpeedEditText).text.toString())
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
