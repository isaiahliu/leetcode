package p17xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun getCollisionTimes(cars: Array<IntArray>): DoubleArray {
            val result = DoubleArray(cars.size) { -1.0 }

            data class Car(val index: Int) {
                var joinTime: Double = Double.MAX_VALUE
                    private set
                val position = cars[index][0]
                val speed = cars[index][1]

                fun setTime(time: Double) {
                    joinTime = time
                    result[index] = time
                }
            }

            val queue = LinkedList<Car>()

            for (carIndex in cars.lastIndex downTo 0) {
                val car = Car(carIndex)
                while (queue.isNotEmpty()) {
                    val nextCar = queue.peek()

                    if (car.speed <= nextCar.speed) {
                        queue.poll()
                        continue
                    }

                    val joinTime = (nextCar.position - car.position).toDouble() / (car.speed - nextCar.speed)

                    if (joinTime - nextCar.joinTime > 0.000001) {
                        queue.poll()
                        continue
                    } else {
                        car.setTime(joinTime)
                        break
                    }
                }

                queue.push(car)
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().getCollisionTimes(
            arrayOf(
                intArrayOf(3, 4), intArrayOf(5, 4), intArrayOf(6, 3), intArrayOf(9, 1)
            )
        ).toList().also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
