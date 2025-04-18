package p25xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun countFairPairs(nums: IntArray, lower: Int, upper: Int): Long {
            val map = TreeMap<Int, Int>()

            nums.forEach {
                map[it] = (map[it] ?: 0) + 1
            }

            var sum = 0
            map.forEach { (num, count) ->
                sum += count

                map[num] = sum
            }

            var result = 0L

            var prevCount = 0
            map.forEach { (num, c) ->
                val count = c - prevCount
                prevCount = c
                val high = upper - num
                val low = maxOf(lower - num, num + 1)

                if (high >= low) {
                    val n1 = map.floorEntry(high)?.value ?: 0
                    val n2 = map.lowerEntry(low)?.value ?: 0

                    result += 1L * count * (n1 - n2)
                }

                if (num + num in lower..upper) {
                    result += 1L * count * (count - 1) / 2
                }
            }

            return result
        }
    }

    expect {
        Solution().countFairPairs(
            intArrayOf(0, 1, 7, 4, 4, 5), 3, 6
        )
    }
}