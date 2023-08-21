package p16xx

import util.expect

fun main() {
    class ParkingSystem(big: Int, medium: Int, small: Int) {
        val pos = intArrayOf(0, big, medium, small)

        fun addCar(carType: Int): Boolean {
            return if (pos[carType] == 0) {
                false
            } else {
                pos[carType]--
                true
            }
        }
    }

    expect {
        ParkingSystem(1, 1, 1).addCar(
            5
        )
    }
}

