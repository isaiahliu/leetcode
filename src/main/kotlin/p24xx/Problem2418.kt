package p24xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun sortPeople(names: Array<String>, heights: IntArray): Array<String> {
            return names.indices.sortedByDescending { heights[it] }.map { names[it] }.toTypedArray()
        }
    }

    measureTimeMillis {
        Solution().sortPeople(
            arrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}