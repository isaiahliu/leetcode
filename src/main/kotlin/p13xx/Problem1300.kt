package p13xx

import java.util.*
import kotlin.math.absoluteValue
import util.expect

fun main() {
    class Solution {
        fun findBestValue(arr: IntArray, target: Int): Int {
            val map = TreeMap<Int, Int>()

            arr.forEach {
                map[it] = (map[it] ?: 0) + 1
            }

            if (arr.sum() <= target) {
                return map.lastKey()
            }

            var best = (target / arr.size).let { intArrayOf(it, it + 1) }.minBy {
                (it * arr.size - target).absoluteValue
            }

            if (best <= map.firstKey()) {
                return best
            }

            var remainingCount = arr.size
            var existingSum = 0

            while (true) {
                val (key, value) = map.pollFirstEntry()

                remainingCount -= value
                existingSum += key * value

                best = ((target - existingSum) / remainingCount).let { intArrayOf(it, it + 1) }.minBy {
                    (it * remainingCount + existingSum - target).absoluteValue
                }

                if (best <= map.firstKey()) {
                    return best
                }
            }
        }
    }

    expect {
        Solution().findBestValue(
            intArrayOf(2, 3, 5), 11
        )
    }
}

