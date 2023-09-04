package p22xx

import util.expect

fun main() {
    class Solution {
        fun maxConsecutive(bottom: Int, top: Int, special: IntArray): Int {
            if (special.isEmpty()) {
                return top - bottom + 1
            }

            special.sort()

            var result = top - special.last()

            special.fold(bottom - 1) { a, b ->
                result = result.coerceAtLeast(b - a - 1)
                b
            }

            return result
        }
    }

    expect(3) {
        Solution().maxConsecutive(
            5, 4, intArrayOf()
        )
    }
}