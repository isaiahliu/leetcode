package p19xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun splitPainting(segments: Array<IntArray>): List<List<Long>> {
            val map = TreeMap<Int, MutableSet<Pair<Int, Long>>>()

            segments.forEach { (start, end, color) ->
                map.computeIfAbsent(start) { hashSetOf() }.add(0 to color.toLong())
                map.computeIfAbsent(end) { hashSetOf() }.add(1 to color.toLong())
            }

            val result = arrayListOf<List<Long>>()

            var (pre, s) = map.pollFirstEntry()
            var colors = s.map { it.second }.sum()

            while (map.isNotEmpty()) {
                val (time, event) = map.pollFirstEntry()

                if (colors > 0) {
                    result.add(listOf(pre.toLong(), time.toLong(), colors))
                }

                event.forEach { (e, c) ->
                    when (e) {
                        0 -> colors += c
                        1 -> colors -= c
                    }
                }

                pre = time
            }

            return result
        }
    }

    expect {
        Solution().splitPainting(
            arrayOf(
                intArrayOf(3, 10),
                intArrayOf(1, 5),
                intArrayOf(2, 6),
            )
        )
    }
}