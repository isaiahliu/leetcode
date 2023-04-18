package p08xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun flipgame(fronts: IntArray, backs: IntArray): Int {
            val counts = IntArray(2001)

            val indices = TreeMap<Int, MutableSet<Int>>()

            fronts.indices.forEach { index ->
                arrayOf(fronts[index], backs[index]).forEach {
                    counts[it]++
                    indices.computeIfAbsent(it) { hashSetOf() }.add(index)
                }
            }

            while (indices.isNotEmpty()) {
                val (num, i) = indices.pollFirstEntry()

                if (i.size == counts[num]) {
                    return num
                }
            }

            return 0
        }
    }

    measureTimeMillis {
        Solution().flipgame(
            intArrayOf(), intArrayOf()
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}