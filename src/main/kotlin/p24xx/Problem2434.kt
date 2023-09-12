package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun robotWithString(s: String): String {
            val result = StringBuilder()

            val counts = IntArray(27)
            counts[26] = 1

            s.forEach {
                counts[it - 'a']++
            }

            var minChar = s.min()
            val queue = LinkedList<Char>()

            var index = 0
            while (index < s.length) {
                while (queue.isNotEmpty() && queue.peek() <= minChar) {
                    result.append(queue.poll())
                }

                val c = s.getOrNull(index++) ?: break
                counts[c - 'a']--

                if (c == minChar) {
                    result.append(c)

                    if (counts[c - 'a'] == 0) {
                        minChar = 'a' + (minChar - 'a' + 1 until counts.size).first { counts[it] > 0 }
                    }
                } else {
                    queue.push(c)
                }
            }

            queue.forEach { result.append(it) }

            return result.toString()
        }
    }

    expect {
        Solution().robotWithString(
            "bdda"
        )
    }
}