package p16xx

import util.expect

fun main() {
    class Solution {
        fun slowestKey(releaseTimes: IntArray, keysPressed: String): Char {
            var result = 'a' - 1
            var max = 0

            var pre = 0

            releaseTimes.forEachIndexed { index, i ->
                if (i - pre > max || i - pre == max && keysPressed[index] > result) {
                    result = keysPressed[index]
                    max = i - pre
                }

                pre = i
            }
            return result
        }
    }

    expect {
        Solution().slowestKey(
            intArrayOf(9, 29, 49, 50), "cbcd"
        )
    }
}