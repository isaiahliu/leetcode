package p17xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun tupleSameProduct(nums: IntArray): Int {
            val prod = hashMapOf<Int, Int>()
            var result = 0
            nums.forEachIndexed { index, num ->
                val tempProd = hashMapOf<Int, Int>()

                for (i in 0 until index) {
                    (nums[i] * num).also {
                        tempProd[it] = (tempProd[it] ?: 0) + 2
                    }
                }

                tempProd.forEach { (p, count) ->
                    val c2 = prod[p] ?: 0

                    result += c2 * count * 2

                    prod[p] = c2 + count
                }
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().tupleSameProduct(
            intArrayOf(2, 3, 4, 6)
        ).also { println("${it} should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
