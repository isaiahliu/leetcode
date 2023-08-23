package p20xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minOperations(grid: Array<IntArray>, x: Int): Int {
            val treeMap = TreeMap<Int, Int>()

            grid.forEach {
                it.forEach {
                    treeMap[it] = (treeMap[it] ?: 0) + 1
                }
            }

            val nums = treeMap.entries.toList()

            val leftCost = IntArray(nums.size)
            var (pre, totalCount) = nums[0]
            var sum = 0

            for (i in 1 until nums.size) {
                val (num, count) = nums[i]

                if ((num - pre) % x != 0) {
                    return -1
                }

                sum += (num - pre) / x * totalCount

                totalCount += count
                pre = num

                leftCost[i] = sum
            }

            var result = sum
            sum = 0
            pre = nums[nums.lastIndex].key
            totalCount = nums[nums.lastIndex].value

            for (i in nums.lastIndex - 1 downTo 0) {
                val (num, count) = nums[i]

                sum += (pre - num) / x * totalCount

                totalCount += count
                pre = num

                result = result.coerceAtMost(sum + leftCost[i])
            }

            return result
        }
    }

    expect {
        Solution().minOperations(
            arrayOf(intArrayOf(931, 128, 639, 712)), 73
        )
    }
}