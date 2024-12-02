import java.io.Serializable

data class Car(
    val id: Int,
    val brand: String,
    val year: Int,
    val mileage: Int,
    val maxSpeed: Int
) : Serializable
