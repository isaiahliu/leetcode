package p11xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class MajorityChecker(arr: IntArray) {
        val map = hashMapOf<Int, TreeMap<Int, Int>>()

        init {
            val sums = hashMapOf<Int, Int>()

            arr.forEachIndexed { index, num ->
                ((sums[num] ?: 0) + 1).also {
                    sums[num] = it
                    map.computeIfAbsent(num) { TreeMap() }[index] = it
                }
            }
        }

        fun query(left: Int, right: Int, threshold: Int): Int {
            return map.entries.firstOrNull { (num, counts) ->
                counts.lowerEntry(right + 1)?.value?.let {
                    it - (counts.lowerEntry(left)?.value ?: 0) >= threshold
                } ?: false
            }?.key ?: -1
        }
    }


    measureTimeMillis {
        MajorityChecker(intArrayOf()).query(
            1, 2, 3
        ).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}