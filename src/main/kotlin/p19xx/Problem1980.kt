package p19xx

import util.expect

fun main() {
    class Solution {
        fun findDifferentBinaryString(nums: Array<String>): String {
            val numSet = nums.toSet()

            val l = nums[0].length
            fun String.dfs(): String? {
                return if (this.length < l) {
                    "${this}0".dfs() ?: "${this}1".dfs()
                } else {
                    this.takeIf { it !in numSet }
                }
            }
            return "".dfs().orEmpty()
        }
    }

    expect {
        Solution().findDifferentBinaryString(
            arrayOf()
        )
    }
}