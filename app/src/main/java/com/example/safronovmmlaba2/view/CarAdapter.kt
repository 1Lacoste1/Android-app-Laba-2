package com.example.safronovmmlaba2.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.safronovmmlaba2.R
import com.example.safronovmmlaba2.model.Car

class CarAdapter(
    private var cars: MutableList<Car>,
    private val onItemClick: (Car) -> Unit,
    private val onDeleteClick: (Car) -> Unit
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val brandText: TextView = itemView.findViewById(R.id.brandTextView)
        val deleteButton: ImageButton = itemView.findViewById(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val car = cars[position]
        holder.brandText.text = car.brand
        holder.itemView.setOnClickListener { onItemClick(car) }
        holder.deleteButton.setOnClickListener { onDeleteClick(car) }
    }

    override fun getItemCount(): Int = cars.size

    fun updateData(newCars: List<Car>) {
        cars.clear()
        cars.addAll(newCars)
        notifyDataSetChanged()
    }
}
