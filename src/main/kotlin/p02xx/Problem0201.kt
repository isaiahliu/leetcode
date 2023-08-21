package p02xx

import util.expect

fun main() {
    class Solution {
        fun rangeBitwiseAnd(left: Int, right: Int): Int {
            if (left == right) {
                return left
            }

            var l = left
            var r = right

            var result = 0
            var h1 = Integer.highestOneBit(l)
            var h2 = Integer.highestOneBit(r)

            while (h1 == h2) {
                result += h1

                l -= h1
                r -= h2

                h1 = Integer.highestOneBit(l)
                h2 = Integer.highestOneBit(r)
            }

            return result
        }
    }

    expect {
        Solution().rangeBitwiseAnd(
            1, 2
        )
    }
}

