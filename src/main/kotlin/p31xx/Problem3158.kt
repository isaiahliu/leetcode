package p31xx

import util.expect

fun main() {
    class Solution {
        fun duplicateNumbersXOR(nums: IntArray): Int {
            var result = 0
            val visited = hashSetOf<Int>()

            nums.forEach {
                if (!visited.add(it)) {
                    result = result xor it
                }
            }

            return result
        }
    }

    expect {
        Solution().duplicateNumbersXOR(
            intArrayOf()
        )
    }
}
