package p04xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun makesquare(matchsticks: IntArray): Boolean {
            var length = matchsticks.sum()

            if (length % 4 != 0) {
                return false
            }

            length /= 4

            matchsticks.sortDescending()

            if (matchsticks[0] > length) {
                return false
            }

            val stack = LinkedList<Int>()
            stack.add(0)
            loop@ while (stack.size < 4) {
                val start = stack.poll() ?: return false
                for (i in start + 1 until (1 shl matchsticks.size)) {
                    if (stack.any { it and i > 0 }) {
                        continue
                    }

                    var sum = 0

                    matchsticks.forEachIndexed { index, num ->
                        if (i and (1 shl index) > 0) {
                            sum += num
                        }
                    }

                    if (sum == length) {
                        stack.push(i)
                        stack.push(i)

                        continue@loop
                    }
                }
            }

            return true
        }
    }

    expect {
        Solution().makesquare(
            intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6)
        )
    }
}