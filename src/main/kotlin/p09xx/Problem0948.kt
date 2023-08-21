package p09xx

import util.expect

fun main() {
class Solution {
    fun bagOfTokensScore(tokens: IntArray, power: Int): Int {
        tokens.sort()

        var t = power

        var left = 0
        var right = tokens.lastIndex

        var score = 0
        var result = 0
        while (left <= right) {
            if (t >= tokens[left]) {
                t -= tokens[left++]
                score++
            } else if (score > 0) {
                t += tokens[right--]
                score--
            } else {
                break
            }

            result = result.coerceAtLeast(score)
        }

        return result
    }
}

    expect {
        Solution().bagOfTokensScore(
            intArrayOf(100), 50
        )
    }
}
