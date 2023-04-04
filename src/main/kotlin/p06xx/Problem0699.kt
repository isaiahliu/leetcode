package p06xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun fallingSquares(positions: Array<IntArray>): List<Int> {
            val map = TreeMap<Int, IntArray>()

            map[0] = intArrayOf(100000000, 0)

            var max = 0
            return positions.map { (left, size) ->
                val (leftBorder, arr) = map.lowerEntry(left)

                if (leftBorder + arr[0] > left) {
                    map[left] = intArrayOf(leftBorder + arr[0] - (left - leftBorder), arr[1])

                    arr[0] = left - leftBorder
                }

                var height = 0
                map.subMap(left, left + size).toMap().forEach { (key, value) ->
                    height = height.coerceAtLeast(value[1])

                    if (key + value[0] > left + size) {
                        map[left + size] = intArrayOf(key + value[0] - left - size, value[1])
                    }

                    map.remove(key)
                }

                height += size

                map[left] = intArrayOf(size, height)

                max = max.coerceAtLeast(height)

                max
            }
        }
    }

    measureTimeMillis {
        Solution().fallingSquares(
            arrayOf(
                intArrayOf(100, 100), intArrayOf(200, 100)
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}