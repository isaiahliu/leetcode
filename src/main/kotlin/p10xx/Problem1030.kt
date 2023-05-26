package p10xx

import kotlin.math.absoluteValue
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun allCellsDistOrder(rows: Int, cols: Int, rCenter: Int, cCenter: Int): Array<IntArray> {
            return (0 until rows).map { r ->
                (0 until cols).map { c ->
                    intArrayOf(r, c)
                }
            }.flatten().sortedBy { (r, c) ->
                (r - rCenter).absoluteValue + (c - cCenter).absoluteValue
            }.toTypedArray()
        }
    }

    measureTimeMillis {
        Solution().allCellsDistOrder(
            1, 2, 3, 4
        ).also {
            println(it)
        }
    }.also { println("Time cost: ${it}ms") }
}
