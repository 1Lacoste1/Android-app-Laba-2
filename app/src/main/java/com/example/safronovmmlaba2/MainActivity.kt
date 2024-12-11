package com.example.safronovmmlaba2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.safronovmmlaba2.databinding.ActivityMainBinding
import com.example.safronovmmlaba2.model.Car
import com.example.safronovmmlaba2.model.CarRepository
import com.example.safronovmmlaba2.presenter.CarListPresenter
import com.example.safronovmmlaba2.view.AddCarActivity
import com.example.safronovmmlaba2.view.CarAdapter
import com.example.safronovmmlaba2.view.CarDetailsActivity
import com.example.safronovmmlaba2.view.CarListView

class MainActivity : AppCompatActivity(), CarListView {
    private lateinit var presenter: CarListPresenter
    private lateinit var adapter: CarAdapter
    private lateinit var binding: ActivityMainBinding // Инициализация View Binding

    companion object {
        private const val EDIT_CAR_REQUEST_CODE = 1
        private const val ADD_CAR_REQUEST_CODE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализация View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация Presenter
        val repository = CarRepository()
        presenter = CarListPresenter(this, repository)

        setupRecyclerView()

        // Загрузка автомобилей
        presenter.loadCars()

        // Обработка нажатия кнопки добавления автомобиля
        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddCarActivity::class.java)
            startActivityForResult(intent, ADD_CAR_REQUEST_CODE)
        }

        // Обработка нажатия кнопки очистки списка
        binding.clearButton.setOnClickListener {
            presenter.clearCars()
        }
    }

    private fun setupRecyclerView() {
        adapter = CarAdapter(
            mutableListOf(),
            onItemClick = { car -> showCarDetails(car) },
            onDeleteClick = { car -> presenter.removeCar(car) }
        )
        binding.recyclerView.apply { // Использование binding для доступа к RecyclerView
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    override fun showCars(cars: List<Car>) {
        adapter.updateData(cars)
    }

    override fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showCarDetails(car: Car) {
        val intent = Intent(this, CarDetailsActivity::class.java).apply {
            putExtra("CAR", car)
        }
        startActivityForResult(intent, EDIT_CAR_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                EDIT_CAR_REQUEST_CODE -> {
                    val updatedCar = data?.getParcelableExtra<Car>("UPDATED_CAR")
                    updatedCar?.let { presenter.updateCar(it) }
                }
                ADD_CAR_REQUEST_CODE -> {
                    val newCar = data?.getParcelableExtra<Car>("NEW_CAR")
                    newCar?.let { presenter.addCar(it) }
                }
            }
        }
    }
}
