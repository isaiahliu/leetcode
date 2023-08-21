package p15xx

import util.expect

fun main() {
    class Solution {
        fun numSplits(s: String): Int {
            val leftSums = IntArray(s.length + 1)
            val rightSums = IntArray(s.length + 1)

            val leftSet = hashSetOf<Char>()
            val rightSet = hashSetOf<Char>()

            for (i in s.indices) {
                leftSet.add(s[i])
                rightSet.add(s[s.lastIndex - i])

                leftSums[i + 1] = leftSet.size
                rightSums[s.lastIndex - i] = rightSet.size
            }

            var result = 0

            leftSums.forEachIndexed { index, c ->
                if (c == rightSums[index]) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().numSplits(
            "aacaba"
        )
    }
}

