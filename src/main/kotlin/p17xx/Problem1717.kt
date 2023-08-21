package p17xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun maximumGain(s: String, x: Int, y: Int): Int {
            val queue = LinkedList<Char>()

            var left = 'a'
            var right = 'b'

            val min = x.coerceAtMost(y)
            val max = x.coerceAtLeast(y)
            if (y > x) {
                left++
                right--
            }

            var result = 0
            s.forEach {
                if (it == right && queue.peekLast() == left) {
                    queue.pollLast()
                    result += max
                } else {
                    queue.add(it)
                }
            }

            queue.toList().also { queue.clear() }.forEach {
                if (it == left && queue.peekLast() == right) {
                    queue.pollLast()
                    result += min
                } else {
                    queue.add(it)
                }
            }

            return result
        }
    }

    expect {
        Solution().maximumGain(
            "cdbcbbaaabab", 4, 5
        )
    }
}
