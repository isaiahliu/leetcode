package p13xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxNumberOfFamilies(n: Int, reservedSeats: Array<IntArray>): Int {
            val posLeft = 0b0111100000
            val posCenter = 0b0001111000
            val posRight = 0b0000011110

            val reserved = hashMapOf<Int, Int>()

            reservedSeats.forEach { (r, c) ->
                reserved[r - 1] = (reserved[r - 1] ?: 0b1111111111) - (1 shl (c - 1))
            }

            var result = n * 2
            reserved.values.forEach { status ->
                val left = status and posLeft == posLeft
                val center = status and posCenter == posCenter
                val right = status and posRight == posRight

                when {
                    left && right -> {
                    }

                    left || center || right -> {
                        result -= 1
                    }

                    else -> {
                        result -= 2
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().maxNumberOfFamilies(
            5, arrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

