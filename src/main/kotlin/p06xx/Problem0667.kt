package p06xx

import kotlin.math.sign
import util.expect

fun main() {
    class Solution {
        fun constructArray(n: Int, k: Int): IntArray {
            val result = IntArray(n) { it + 1 }

            if (k > 1) {
                var currentIndex = 1
                var currentNum = k + 1
                var t = k

                while (t != 0) {
                    result[currentIndex++] = currentNum

                    t -= t.sign
                    t = -t

                    currentNum += t
                }
            }

            return result
        }
    }

    expect {
        Solution().constructArray(
            3, 2
        ).toList()
    }
}