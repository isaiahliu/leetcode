package p31xx

import util.expect

fun main() {
    class Solution {
        fun maxTotalReward(rewardValues: IntArray): Int {
            val sortedValues = rewardValues.distinct().sorted()

            val cache = hashMapOf<Int, Boolean>()

            fun check(sum: Int): Boolean {
                cache[sum]?.also {
                    return it
                }

                val target = sum / 2 + 1
                var l = 0
                var r = sortedValues.lastIndex
                var startIndex = 0

                while (l <= r) {
                    val mid = (l + r) / 2

                    if (sortedValues[mid] >= target) {
                        startIndex = mid
                        r = mid - 1
                    } else {
                        l = mid + 1
                    }
                }

                for (index in startIndex until sortedValues.size) {
                    val num = sortedValues[index]

                    when {
                        num > sum -> break
                        num == sum -> true
                        else -> check(sum - num)
                    }.takeIf { it }?.also {
                        cache[sum] = true
                        return true
                    }
                }

                cache[sum] = false
                return false
            }

            var result = sortedValues.last() * 2 - 1

            while (!check(result)) {
                result--
            }

            return result
        }
    }

    expect {
        Solution().maxTotalReward(
            intArrayOf(50000, 50000, 50000, 50000, 50000)
        )
    }
}
