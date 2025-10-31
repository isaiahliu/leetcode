package p32xx

import util.expect

fun main() {
    class Solution {
        fun getSneakyNumbers(nums: IntArray): IntArray {
            return buildList {
                val visited = hashSetOf<Int>()
                nums.forEach {
                    if (!visited.add(it)) {
                        add(it)
                    }
                }
            }.toIntArray()
        }
    }

    expect {
        Solution().getSneakyNumbers(
            intArrayOf()
        )
    }
}
