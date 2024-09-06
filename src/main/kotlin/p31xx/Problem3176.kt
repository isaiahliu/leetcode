package p31xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumLength(nums: IntArray, k: Int): Int {
            val dp = hashMapOf<Int, TreeMap<Int, Int>>()

            nums.forEach {
                val current = TreeMap<Int, Int>()

                dp.forEach { (prev, tree) ->
                    val add = if (prev == it) 0 else 1

                    tree.forEach { (count, length) ->
                        val newCount = count + add
                        if (newCount <= k && current.floorEntry(newCount)?.takeIf { it.value >= length + 1 } == null) {
                            while (true) {
                                current.ceilingEntry(newCount)?.takeIf { length + 1 >= it.value }?.also {
                                    current.remove(it.key)
                                } ?: break
                            }

                            current[newCount] = length + 1
                        }
                    }

                }

                current.putIfAbsent(0, 1)
                dp[it] = current
            }

            return dp.values.maxOf { it.lastEntry().value }
        }
    }

    expect {
        Solution().maximumLength(
            intArrayOf(1, 2, 1, 1, 3), 2
        )
    }
}
