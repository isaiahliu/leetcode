package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun partitionArray(nums: IntArray, k: Int): Int {
            val set = TreeSet<Int>()
            nums.forEach { set.add(it) }

            var result = 1
            var left = set.first()

            while (true) {
                left = set.higher(left + k) ?: break
                result++
            }

            return result
        }
    }

    expect {
        Solution().partitionArray(
            intArrayOf(1, 3, 5, 2, 4, 8, 2, 2), 1
        )
    }
}