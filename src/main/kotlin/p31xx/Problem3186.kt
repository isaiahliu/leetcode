package p31xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumTotalDamage(power: IntArray): Long {
            val dp = LinkedList<Pair<Long, Long>>()
            var preMax = 0L
            power.toList().groupingBy { it.toLong() }.eachCount().entries.sortedBy { it.key }.forEach { (damage, count) ->
                while (dp.peekFirst()?.first?.takeIf { it + 2 < damage } != null) {
                    preMax = maxOf(preMax, dp.poll().second)
                }

                dp.add(damage to damage * count + preMax)
            }

            return dp.maxOf { it.second }
        }
    }

    expect {
        Solution().maximumTotalDamage(
            intArrayOf(5, 9, 2, 10, 2, 7, 10, 9, 3, 8)
        )
    }
}
