package p26xx

import util.expect

fun main() {
    class Solution {
        fun doesValidArrayExist(derived: IntArray): Boolean {
            return derived.fold(0, Int::xor) == 0
        }
    }

    expect {
        Solution().doesValidArrayExist(
            intArrayOf()
        )
    }
}
