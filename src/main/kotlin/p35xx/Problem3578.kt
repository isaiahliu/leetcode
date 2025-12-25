package p35xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun countPartitions(nums: IntArray, k: Int): Int {
            val m = 1000000007L

            val dp = LongArray(nums.size + 1)
            val sums = LongArray(nums.size + 1)

            dp[0] = 1
            dp[1] = 1
            sums[0] = 1
            sums[1] = 2

            val treeMap = TreeMap<Int, Int>()
            treeMap[nums[0]] = 1
            var leftIndex = 0

            for (index in 1 until nums.size) {
                val num = nums[index]

                treeMap[num] = (treeMap[num] ?: 0) + 1

                val valid = num - k..num + k
                while (treeMap.firstKey() !in valid || treeMap.lastKey() !in valid) {
                    treeMap[nums[leftIndex]]?.also {
                        if (it == 1) {
                            treeMap.remove(nums[leftIndex])
                        } else {
                            treeMap[nums[leftIndex]] = it - 1
                        }
                    }

                    leftIndex++
                }

                val count = (sums[index] - (sums.getOrNull(leftIndex - 1) ?: 0)).mod(m)

                dp[index + 1] = count
                sums[index + 1] = sums[index] + count
            }

            return dp.last().toInt()
        }
    }

    expect {
        Solution().countPartitions(
            intArrayOf(9, 4), 10
        )
    }
}
