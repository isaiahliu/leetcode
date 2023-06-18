package p12xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun maxSumDivThree(nums: IntArray): Int {
            val result = nums.sum()
            val m = result % 3

            if (m == 0) {
                return result
            }

            nums.sort()

            val heap = TreeSet<Int>()

            var minSum = Int.MAX_VALUE
            for (num in nums) {
                when {
                    num % 3 == 0 -> {
                        continue
                    }

                    num > minSum -> {
                        break
                    }

                    num % 3 == m -> {
                        minSum = num
                        break
                    }

                    else -> {
                        for (s in heap.toSet()) {
                            val t = num + s
                            when (t % 3) {
                                0 -> {
                                }

                                m -> {
                                    minSum = t
                                }

                                else -> {
                                    heap.add(t)
                                }
                            }
                        }

                        heap.add(num)
                    }
                }

            }

            return result - minSum
        }
    }

    measureTimeMillis {
        Solution().maxSumDivThree(
            intArrayOf(
                2, 3, 36, 8, 32, 38, 3, 30, 13, 40
            )
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}