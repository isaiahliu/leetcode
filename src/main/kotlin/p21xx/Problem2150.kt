package p21xx

import util.expect

fun main() {
    class Solution {
        fun findLonely(nums: IntArray): List<Int> {
            val all = nums.toSet()

            val visited = hashSetOf<Int>()
            val result = hashSetOf<Int>()

            nums.forEach { num ->
                when {
                    !visited.add(num) -> result.remove(num)
                    num + 1 !in all && num - 1 !in all -> result.add(num)
                }
            }

            return result.toList()
        }
    }

    expect {
        Solution().findLonely(
            intArrayOf()
        )
    }
}