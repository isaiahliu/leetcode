package p14xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun numSteps(s: String): Int {
            val ones = LinkedList<Int>()
            s.forEachIndexed { index, c ->
                if (c == '1') {
                    ones.push(s.length - index - 1)
                }
            }

            var result = 0

            while (ones.size > 1) {
                result++
                var lastOne = ones.poll()

                while (ones.peek() == lastOne + 1) {
                    lastOne = ones.poll()
                }

                ones.push(lastOne + 1)
            }

            return result + ones.poll()
        }
    }

    expect {
        Solution().numSteps(
            "10"
        )
    }
}

