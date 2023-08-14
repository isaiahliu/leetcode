package p18xx

import kotlin.system.measureTimeMillis

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

    measureTimeMillis {
        Solution().reinitializePermutation(
            4
        ).also { println("$it should be $it") }
    }.also { println("Time cost: ${it}ms") }
}
