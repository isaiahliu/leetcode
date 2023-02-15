package p00xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun permute(nums: IntArray): List<List<Int>> {
            val all = (1 shl nums.size) - 1

            val result = arrayListOf<List<Int>>()

            fun walk(used: Int, route: List<Int>) {
                if (used == all) {
                    result.add(route)
                    return
                }

                repeat(nums.size) {
                    if (used and (1 shl it) == 0) {
                        walk(used + (1 shl it), route + nums[it])
                    }
                }
            }

            walk(0, emptyList())

            return result
        }
    }

    measureTimeMillis {
        println(Solution().permute(intArrayOf(1, 2, 3)))
    }.also { println("Time cost: ${it}ms") }
}


