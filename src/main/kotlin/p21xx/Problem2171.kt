package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minimumRemoval(beans: IntArray): Long {
            val counts = TreeMap<Int, Int>()

            var sum = 0L
            beans.forEach {
                sum += it
                counts[it] = (counts[it] ?: 0) + 1
            }

            var result = sum

            var remainCount = beans.size
            var cost = 0L
            while (counts.isNotEmpty()) {
                val (num, count) = counts.pollFirstEntry()

                sum -= 1L * num * count
                remainCount -= count

                result = result.coerceAtMost(sum - 1L * remainCount * num + cost)

                cost += 1L * num * count
            }

            return result
        }
    }

    expect {
        Solution().minimumRemoval(
            IntArray(20000) { 100000 }
        )
    }
}