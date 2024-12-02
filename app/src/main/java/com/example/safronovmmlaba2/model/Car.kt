package com.example.safronovmmlaba2.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Car(
    val id: Int,
    val brand: String,
    val year: Int,
    val mileage: Int,
    val maxSpeed: Int
) : Parcelable
