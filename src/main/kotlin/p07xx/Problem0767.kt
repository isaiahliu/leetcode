package p07xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun reorganizeString(s: String): String {
            val queue = PriorityQueue<Pair<Int, Char>>(compareByDescending { it.first })
            s.toList().groupingBy { it }.eachCount().forEach { (c, count) ->
                queue.add(count to c)
            }

            queue.peek()?.first?.let {
                if (it > (s.length - it + 1)) {
                    return ""
                }
            }

            val result = StringBuilder()
            var lastChar = ' '

            while (queue.isNotEmpty()) {
                val (topCount, topChar) = queue.poll()

                if (topChar == lastChar) {
                    val (secCount, secChar) = queue.poll()
                    queue.add(topCount to topChar)

                    result.append(secChar)

                    if (secCount > 1) {
                        queue.add(secCount - 1 to secChar)
                    }

                    lastChar = secChar
                } else {
                    result.append(topChar)

                    if (topCount > 1) {
                        queue.add(topCount - 1 to topChar)
                    }

                    lastChar = topChar
                }
            }

            return result.toString()
        }
    }

    expect {
        Solution().reorganizeString(
            "aaab"
        )
    }
}