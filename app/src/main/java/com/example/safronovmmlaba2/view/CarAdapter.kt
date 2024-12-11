package com.example.safronovmmlaba2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.safronovmmlaba2.databinding.ItemCarBinding
import com.example.safronovmmlaba2.model.Car

class CarAdapter(
    private var cars: MutableList<Car>,
    private val onItemClick: (Car) -> Unit,
    private val onDeleteClick: (Car) -> Unit
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    inner class CarViewHolder(val binding: ItemCarBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]

        // Установка данных
        holder.binding.brandTextView.text = car.brand

        // Обработка нажатий
        holder.binding.root.setOnClickListener { onItemClick(car) }
        holder.binding.deleteButton.setOnClickListener { onDeleteClick(car) }
    }

    override fun getItemCount(): Int = cars.size

    fun updateData(newCars: List<Car>) {
        cars.clear()
        cars.addAll(newCars)
        notifyDataSetChanged()
    }
}
