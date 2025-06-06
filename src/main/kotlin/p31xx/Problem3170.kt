package p31xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun clearStars(s: String): String {
            val indices = PriorityQueue(compareBy<Int> { s[it] }.thenByDescending { it })

            val removedIndices = hashSetOf<Int>()
            s.forEachIndexed { index, ch ->
                if (ch == '*') {
                    removedIndices += index
                    removedIndices += indices.poll()
                } else {
                    indices.offer(index)
                }
            }

            return buildString {
                s.forEachIndexed { index, ch ->
                    if (index !in removedIndices) {
                        append(ch)
                    }
                }
            }
        }
    }

    expect {
        Solution().clearStars(
            "aaba*"
        )
    }
}
