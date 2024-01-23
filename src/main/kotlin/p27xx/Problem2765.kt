package p27xx

import util.expect

fun main() {
    class Solution {
        fun alternatingSubarray(nums: IntArray): Int {
            var result = -1
            var size = 1
            nums.reduce { a, b ->
                when {
                    b == a - 1 && size % 2 == 0 -> size++
                    b == a + 1 && size % 2 == 1 -> size++
                    b == a + 1 -> size = 2
                    else -> size = 1
                }

                result = maxOf(result, size)

                b
            }

            return result.takeIf { it > 1 } ?: -1
        }
    }

    expect {
        Solution().alternatingSubarray(
            intArrayOf()
        )
    }
}
