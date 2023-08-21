package p17xx

import util.expect

fun main() {
    class Solution {
        fun decode(encoded: IntArray, first: Int): IntArray {
            var t = first

            return IntArray(encoded.size + 1) {
                if (it > 0) {
                    t = t xor encoded[it - 1]
                }

                t
            }
        }
    }

    expect {
        Solution().decode(
            intArrayOf(), 1
        )
    }
}
