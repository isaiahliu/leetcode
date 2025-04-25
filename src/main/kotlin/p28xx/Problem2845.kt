package p28xx

import util.expect
import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {
    class Solution {
        fun countInterestingSubarrays(nums: List<Int>, modulo: Int, k: Int): Long {
            val counts = hashMapOf(0 to 1)
            var result = 0L

            var sum = 0

            nums.forEach {
                sum += 1 - (it % modulo - k).sign.absoluteValue
                counts[(sum - k + modulo) % modulo]?.also {
                    result += it
                }

                counts[sum % modulo] = (counts[sum % modulo] ?: 0) + 1
            }

            return result
        }
    }

    expect {
        Solution().countInterestingSubarrays(
            listOf(), 1, 1
        )
    }
}
