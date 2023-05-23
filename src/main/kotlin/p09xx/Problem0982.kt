package p09xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun countTriplets(nums: IntArray): Int {
            val bits = IntArray(17) {
                1 shl it
            }

            val emptyIndices = Array(17) { hashSetOf<Int>() }

            nums.forEachIndexed { numIndex, num ->
                bits.forEachIndexed { index, i ->
                    if (num and i == 0) {
                        emptyIndices[index].add(numIndex)
                    }
                }
            }

            val cache = hashMapOf<Int, Int>()

            var result = 0
            for (i in nums.indices) {
                val num1 = nums[i]
                for (j in i until nums.size) {
                    val t = nums[j] and num1

                    var times = 1
                    if (i != j) {
                        times++
                    }

                    result += when (t) {
                        0 -> {
                            nums.size * times
                        }

                        in cache -> {
                            (cache[t] ?: 0) * times
                        }

                        else -> {
                            var indices: MutableSet<Int>? = null

                            bits.forEachIndexed { index, bit ->
                                if (t and bit > 0) {
                                    indices?.retainAll(emptyIndices[index]) ?: run {
                                        indices = emptyIndices[index].toMutableSet()
                                    }
                                }
                            }

                            (indices?.size ?: 0).also {
                                cache[t] = it
                            } * times
                        }
                    }
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().countTriplets(
            intArrayOf(2, 1, 3)
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}
