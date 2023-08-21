package p06xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun findLongestChain(pairs: Array<IntArray>): Int {
            pairs.sortWith(compareBy<IntArray> { it[1] }.thenBy { it[0] })

            val map = TreeMap<Int, Int>()
            map[Int.MIN_VALUE] = 0

            pairs.forEach { (start, end) ->
                val count = map.lowerEntry(start).value + 1

                if (map.lowerEntry(end + 1).value < count) {
                    while (true) {
                        map.higherEntry(end)?.takeIf { it.value <= count }?.key?.also {
                            map.remove(it)
                        } ?: break
                    }

                    map[end] = count
                }

            }

            return map.lastEntry().value
        }
    }

    expect {
        Solution().findLongestChain(
            arrayOf(intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4))
        )
    }
}