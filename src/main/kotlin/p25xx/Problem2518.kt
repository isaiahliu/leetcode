package p25xx

import util.expect

fun main() {
    class Solution {
        fun countPartitions(nums: IntArray, k: Int): Int {
            val m = 1000000007
            fun MutableMap<Int, Long>.append(l: Int, r: Int, count: Long) {
                val l2 = maxOf(l, 0)
                val r2 = maxOf(r, 0)

                val key = if (l2 < r2) {
                    l2 * 1001 + r2
                } else {
                    r2 * 1001 + l2
                }

                this[key] = ((this[key] ?: 0) + count) % m
            }

            var counts = hashMapOf(k * 1002 to 1L)

            nums.forEach {
                val newCounts = hashMapOf<Int, Long>()

                counts.forEach { (key, count) ->
                    val l = key % 1001
                    val r = key / 1001
                    newCounts.append(l - it, r, count)
                    newCounts.append(l, r - it, count)
                }

                counts = newCounts
            }

            return counts[0]?.toInt() ?: 0
        }
    }

    expect {
        Solution().countPartitions(
            intArrayOf(790, 555, 729, 447, 538, 657, 258, 716, 645, 349, 148, 860, 425, 401, 282, 889, 309, 720, 228, 39, 366, 107, 765, 546, 791, 938, 154, 85, 845, 656), 558
        )
    }
}