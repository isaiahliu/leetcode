package p18xx

import util.expect

fun main() {
    class Solution {
        fun reinitializePermutation(n: Int): Int {
            var nums = IntArray(n) { it }

            var result = 0

            while (true) {
                result++

                var success = true
                nums = IntArray(n) { index ->
                    nums[index / 2 + n / 2 * (index % 2)].also {
                        success = success and (index == it)
                    }
                }

                if (success) {
                    return result
                }
            }
        }
    }

    expect {
        Solution().reinitializePermutation(
            4
        )
    }
}
