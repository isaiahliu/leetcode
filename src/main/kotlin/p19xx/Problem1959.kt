package p19xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minSpaceWastedKResizing(nums: IntArray, k: Int): Int {
            val greaterNums = Array(nums.size) {
                hashSetOf<Int>()
            }

            val greater = LinkedList<Int>()

            for (index in nums.lastIndex downTo 0) {
                val num = nums[index]

                while (greater.isNotEmpty() && greater.peek() < num) {
                    greater.poll()
                }

                greater.push(num)

                greaterNums[index].addAll(greater)
            }

            val dp = Array(nums.size) {
                //remainK -- currentSize -- waste
                Array(k + 1) { hashMapOf<Int, Int>() }
            }

            greater.forEach {
                dp[0][k][it] = it - nums[0]
            }

            fun MutableMap<Int, Int>.renew(size: Int, waste: Int) {
                this[size] = (this[size] ?: Int.MAX_VALUE).coerceAtMost(waste)
            }

            for (index in 1 until dp.size) {
                val last = dp[index - 1]
                val current = dp[index]

                val num = nums[index]

                last.forEachIndexed { remainK, wastes ->
                    var best = Int.MAX_VALUE
                    wastes.forEach { (size, waste) ->
                        if (size >= num) {
                            current[remainK].renew(size, waste + size - num)
                        }

                        best = best.coerceAtMost(waste)
                    }

                    if (best < Int.MAX_VALUE) {
                        greaterNums[index].forEach {
                            current.getOrNull(remainK - 1)?.renew(it, best + it - num)
                        }
                    }
                }
            }

            return dp[dp.lastIndex].map {
                it.values.minOrNull() ?: Int.MAX_VALUE
            }.min()
        }
    }

    expect {
        Solution().minSpaceWastedKResizing(
            intArrayOf(39, 41, 11, 35, 47, 16, 11, 30, 25, 18), 1
        )

        Solution().minSpaceWastedKResizing(
            intArrayOf(10, 20, 15, 30, 20), 2
        )

        Solution().minSpaceWastedKResizing(
            intArrayOf(10, 20), 0
        )

        Solution().minSpaceWastedKResizing(
            intArrayOf(10, 20, 30), 1
        )


        Solution().minSpaceWastedKResizing(
            intArrayOf(13, 46, 42, 47, 35), 4
        )
    }
}