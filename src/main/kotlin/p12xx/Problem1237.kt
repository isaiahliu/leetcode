package p12xx

import util.expect

fun main() {
    class CustomFunction {
        // Returns f(x, y) for any given positive integers x and y.
        // Note that f(x, y) is increasing with respect to both x and y.
        // i.e. f(x, y) < f(x + 1, y), f(x, y) < f(x, y + 1)
        fun f(x: Int, y: Int): Int {
            return 0
        }
    }

    class Solution {
        fun findSolution(customfunction: CustomFunction, z: Int): List<List<Int>> {
            val result = arrayListOf<List<Int>>()

            fun binarySearch(x: Int, startY: Int, endY: Int): Int? {
                if (startY > endY) {
                    return null
                }

                val midY = (startY + endY) / 2

                val f = customfunction.f(x, midY)

                return when {
                    f < z -> {
                        binarySearch(x, midY + 1, endY)
                    }

                    f > z -> {
                        binarySearch(x, startY, midY - 1)
                    }

                    else -> {
                        midY
                    }
                }
            }

            loop@ for (x in 1..1000) {
                when {
                    customfunction.f(x, 1) > z -> {
                        break@loop
                    }

                    customfunction.f(x, 1000) < z -> {
                        continue@loop
                    }

                    else -> {
                        binarySearch(x, 1, 1000)?.also {
                            result.add(listOf(x, it))
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().findSolution(
            CustomFunction(), 5
        )
    }
}

