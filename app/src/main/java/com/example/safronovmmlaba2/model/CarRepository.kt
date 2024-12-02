package com.example.safronovmmlaba2.model

class CarRepository {
    private val cars = mutableListOf<Car>(
        Car(id = 1, brand = "Toyota", year = 2020, mileage = 15000, maxSpeed = 180),
        Car(id = 2, brand = "Honda", year = 2019, mileage = 25000, maxSpeed = 200),
        Car(id = 3, brand = "Ford", year = 2018, mileage = 30000, maxSpeed = 190)
    )

    fun getAllCars(): List<Car> = cars

    fun addCar(car: Car) {
        cars.add(car)
    }

    fun removeCar(car: Car) {
        cars.remove(car)
    }

    fun clearAllCars() {
        cars.clear()
    }

    fun updateCar(car: Car) {
        val index = cars.indexOfFirst { it.id == car.id }
        if (index != -1) {
            cars[index] = car
        }
    }
}
