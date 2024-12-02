package com.example.safronovmmlaba2.view

import com.example.safronovmmlaba2.model.Car

interface CarListView {
    fun showCars(cars: List<Car>)
    fun showError(message: String)
}