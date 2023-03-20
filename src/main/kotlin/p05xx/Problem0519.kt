package p05xx

import kotlin.random.Random
import kotlin.system.measureTimeMillis

fun main() {
    class Solution(val m: Int, val n: Int) {
        val map = hashMapOf<Int, Int>()

        var size = 0

        init {
            reset()
        }

        fun flip(): IntArray {
            val numIndex = Random.nextInt(size--)

            val result = map[numIndex] ?: numIndex
            map[numIndex] = map[size] ?: size

            println(result)

            return intArrayOf(result / n, result % n)
        }

        fun reset() {
            size = m * n
            map.clear()
        }
    }

    measureTimeMillis {
        val sol = Solution(3, 1)
        repeat(3) {
            sol.flip().toList().also { println(it) }
        }
    }.also { println("Time cost: ${it}ms") }
}