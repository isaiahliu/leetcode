package p16xx

import util.expect

fun main() {
    class Solution {
        fun getSmallestString(n: Int, k: Int): String {
            val result = StringBuilder()

            var remainN = n
            var remainK = k

            val zCount = (remainK - remainN) / 25

            result.append("z".repeat(zCount))
            remainN -= zCount
            remainK -= zCount * 26

            if (remainN > 0) {
                val mid = remainK - remainN

                result.insert(0, 'a' + mid)

                remainN--
            }

            result.insert(0, "a".repeat(remainN))

            return result.toString()
        }
    }

    expect {
        Solution().getSmallestString(
            5, 73
        )
    }
}

