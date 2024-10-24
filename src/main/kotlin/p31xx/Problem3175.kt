package p31xx

import util.expect

fun main() {
    class Solution {
        fun findWinningPlayer(skills: IntArray, k: Int): Int {
            var result = 0
            var winCount = -1

            skills.forEachIndexed { index, num ->
                if (num > skills[result]) {
                    result = index
                    winCount = 0
                }

                if (++winCount == k) {
                    return result
                }
            }

            return result
        }
    }

    expect {
        Solution().findWinningPlayer(
            intArrayOf(), 1
        )
    }
}
