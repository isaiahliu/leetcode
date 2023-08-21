package p02xx

import util.expect

fun main() {
    class Solution {
        fun getHint(secret: String, guess: String): String {
            val leftCounts = IntArray(10)
            val rightCounts = IntArray(10)

            var aCount = 0
            secret.forEachIndexed { index, l ->
                val r = guess[index]

                if (l == r) {
                    aCount++
                } else {
                    leftCounts[l - '0']++
                    rightCounts[r - '0']++
                }
            }

            var bCount = 0
            leftCounts.forEachIndexed { index, l ->
                val r = rightCounts[index]

                bCount += l.coerceAtMost(r)
            }

            return "${aCount}A${bCount}B"
        }
    }

    expect {
        Solution().getHint("1807", "7810")
    }
}

