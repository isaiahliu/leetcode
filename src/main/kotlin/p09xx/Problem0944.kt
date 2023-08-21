package p09xx

import util.expect

fun main() {
class Solution {
    fun minDeletionSize(strs: Array<String>): Int {
        var result = 0
        repeat(strs[0].length) {
            for (i in 1 until strs.size) {
                if (strs[i][it] <= strs[i - 1][it]) {
                    result++
                    break
                }
            }
        }

        return result
    }
}

    expect {
        Solution().minDeletionSize(
            arrayOf(
                "aaa", "bbb", "ccc", "cda"
            )
        )
    }
}
