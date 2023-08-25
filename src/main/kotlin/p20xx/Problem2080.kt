package p20xx

import util.expect
import java.util.*

fun main() {
    class RangeFreqQuery(arr: IntArray) {
        val numMap = hashMapOf<Int, TreeMap<Int, Int>>()

        init {
            arr.forEachIndexed { index, i ->
                numMap.computeIfAbsent(i) { TreeMap<Int, Int>().also { it[-1] = 0 } }.also {
                    it[index] = it.lastEntry().value + 1
                }
            }
        }

        fun query(left: Int, right: Int, value: Int): Int {
            return numMap[value]?.let {
                it.floorEntry(right).value - it.lowerEntry(left).value
            } ?: 0
        }
    }

    expect {
        RangeFreqQuery(intArrayOf()).query(
            1, 2, 3
        )
    }
}