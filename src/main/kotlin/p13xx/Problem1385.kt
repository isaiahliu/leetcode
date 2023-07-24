package p13xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun findTheDistanceValue(arr1: IntArray, arr2: IntArray, d: Int): Int {
            val set = TreeSet(arr2.toSet())

            return arr1.count { num ->
                set.ceiling(num)?.takeIf { it - num <= d } == null && set.floor(num)?.takeIf { num - it <= d } == null
            }
        }
    }

    measureTimeMillis {
        Solution().findTheDistanceValue(
            intArrayOf(4, 5, 8), intArrayOf(10, 9, 1, 8), 2
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}

