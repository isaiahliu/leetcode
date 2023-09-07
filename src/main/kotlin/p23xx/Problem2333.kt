package p23xx

import util.expect
import java.util.*
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun minSumSquareDiff(nums1: IntArray, nums2: IntArray, k1: Int, k2: Int): Long {
            val map = TreeMap<Long, Int>(compareByDescending { it })
            var result = 0L
            nums1.indices.forEach {
                (nums1[it] - nums2[it]).absoluteValue.toLong().takeIf { it > 0 }?.also {
                    result += it * it
                    map[it] = (map[it] ?: 0) + 1
                }
            }

            var remain = k1 + k2
            while (remain > 0 && map.isNotEmpty()) {
                val (num, count) = map.pollFirstEntry()
                val targetNum = map.keys.firstOrNull() ?: 0

                when {
                    remain <= count -> {
                        result -= num * num * remain
                        result += (num - 1) * (num - 1) * remain

                        remain = 0
                    }

                    remain < (num - targetNum) * count -> {
                        val deductNum = remain / count

                        result -= num * num * count
                        result += (num - deductNum) * (num - deductNum) * count

                        remain -= deductNum * count
                        if (num > deductNum) {
                            map[num - deductNum] = (map[num - deductNum] ?: 0) + count
                        }
                    }

                    else -> {
                        result -= num * num * count
                        result += targetNum * targetNum * count

                        remain -= (num - targetNum).toInt() * count
                        if (targetNum > 0) {
                            map[targetNum] = (map[targetNum] ?: 0) + count
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minSumSquareDiff(
            intArrayOf(1, 4, 10, 12), intArrayOf(5, 8, 6, 9), 10, 5
        )
    }
}