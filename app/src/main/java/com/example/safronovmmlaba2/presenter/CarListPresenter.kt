package com.example.safronovmmlaba2.presenter

import com.example.safronovmmlaba2.model.Car
import com.example.safronovmmlaba2.model.CarRepository
import com.example.safronovmmlaba2.view.CarListView

class CarListPresenter(
    private val view: CarListView,
    private val repository: CarRepository
) {
    fun loadCars() {
        val cars = repository.getAllCars()
        if (cars.isEmpty()) {
            view.showError("No cars available")
        } else {
            view.showCars(cars)
        }
    }

    fun addCar(car: Car) {
        repository.addCar(car)
        loadCars()
    }

    fun removeCar(car: Car) {
        repository.removeCar(car)
        loadCars()
    }

    fun clearCars() {
        repository.clearAllCars() // Очистка репозитория
        view.showCars(repository.getAllCars()) // Обновление списка на экране
    }

    fun updateCar(car: Car) {
        repository.updateCar(car)
        view.showCars(repository.getAllCars())
    }
}