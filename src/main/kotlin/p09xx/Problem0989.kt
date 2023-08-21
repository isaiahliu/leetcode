package p09xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun addToArrayForm(num: IntArray, k: Int): List<Int> {
            var n = 0

            var t = k

            val result = LinkedList<Int>()

            for (i in num.lastIndex downTo 0) {
                n += num[i] + (t % 10)

                result.push(n % 10)
                n /= 10
                t /= 10
            }

            n += t

            while (n > 0) {
                result.push(n % 10)
                n /= 10
            }

            return result
        }
    }

    expect {
        Solution().addToArrayForm(
            intArrayOf(1, 2, 0, 0), 34
        )
    }
}
