package p30xx

import util.expect
import kotlin.math.abs
import kotlin.math.min

fun main() {
    class Solution {
        fun sumOfPowers(nums: IntArray, k: Int): Int {
            val m = 1000000007
            val inf = 0x3f3f3f3f
            nums.sort()

            var result = 0
            val d = Array(nums.size) {
                Array(k + 1) { hashMapOf<Int, Int>() }
            }

            for (i in nums.indices) {
                d[i][1][inf] = 1
                for (j in 0 until i) {
                    val diff = abs(nums[i] - nums[j])
                    for (p in 2..k) {
                        for ((v, cnt) in d[j][p - 1]) {
                            val currKey = min(diff, v)
                            d[i][p][currKey] = ((d[i][p][currKey] ?: 0) + cnt) % m
                        }
                    }
                }

                for ((v, cnt) in d[i][k]) {
                    result = ((result + 1L * v * cnt % m) % m).toInt()
                }
            }
            return result
        }
    }

    expect {
        Solution().sumOfPowers(
            intArrayOf(1, 1, 0, 0, 0, 1, 1, 0, 0, 1), 3
        )
    }
}
