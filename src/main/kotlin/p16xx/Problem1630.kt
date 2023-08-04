package p16xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun checkArithmeticSubarrays(nums: IntArray, l: IntArray, r: IntArray): List<Boolean> {
            return l.mapIndexed { index, from ->
                val to = r[index]

                nums.slice(from..to).sorted().let {
                    when (it.size) {
                        0, 1, 2 -> true
                        else -> {
                            val delta = it[1] - it[0]

                            for (i in 2 until it.size) {
                                if (it[i] - it[i - 1] != delta) {
                                    return@let false
                                }
                            }

                            true
                        }
                    }
                }
            }
        }
    }

    measureTimeMillis {
        Solution().checkArithmeticSubarrays(
            intArrayOf(), intArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}