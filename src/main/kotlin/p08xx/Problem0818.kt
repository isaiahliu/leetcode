package p08xx

import kotlin.math.sign
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun racecar(target: Int): Int {
            val posRange = 0..Integer.highestOneBit(target) * 2
            val visited = hashSetOf(0 to 1)
            val tasks = hashSetOf(0 to 1)

            var result = 0

            while (true) {
                tasks.toSet().also { tasks.clear() }.forEach { (pos, speed) ->
                    if (pos == target) {
                        return result
                    }

                    arrayOf(pos to (speed.sign * -1), (pos + speed) to (speed * 2)).forEach {
                        if (it.first in posRange && visited.add(it)) {
                            tasks.add(it)
                        }
                    }
                }
                result++
            }
        }
    }

    measureTimeMillis {
        Solution().racecar(
            5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}