package p16xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minimumDeviation(nums: IntArray): Int {
            val set = TreeSet<Int>()

            nums.forEach {
                if (it % 2 == 1) {
                    set.add(it * 2)
                } else {
                    set.add(it)
                }
            }

            var result = set.last() - set.first()
            while (true) {
                set.pollLast()?.takeIf { it % 2 == 0 }?.also {
                    set.add(it / 2)
                    result = result.coerceAtMost(set.last() - set.first())
                } ?: break
            }

            return result
        }
    }

    expect {
        Solution().minimumDeviation(
            intArrayOf(4, 9, 4, 5)
        )

        Solution().minimumDeviation(
            intArrayOf(1, 2, 3, 4)
        )
    }
}

