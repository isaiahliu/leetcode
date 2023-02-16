package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun subsets(nums: IntArray): List<List<Int>> {
            val result = arrayListOf(emptyList<Int>())
            fun walk(index: Int, route: List<Int>) {
                if (index == nums.size) {
                    return
                }

                (route + nums[index]).also {
                    result.add(it)

                    walk(index + 1, it)
                }
                walk(index + 1, route)
            }

            walk(0, emptyList())
            return result
        }
    }

    measureTimeMillis {
        println(Solution().subsets(intArrayOf(1, 2, 3)))
    }.also { println("Time cost: ${it}ms") }
}

