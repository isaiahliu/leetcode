package p14xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun processQueries(queries: IntArray, m: Int): IntArray {
            val nums = MutableList(m) { it + 1 }

            return queries.map {
                var index = 0

                while (nums[index] != it) {
                    index++
                }

                nums.removeAt(index)
                nums.add(0, it)

                index
            }.toIntArray()
        }
    }

    measureTimeMillis {
        Solution().processQueries(
            intArrayOf(), 5
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

