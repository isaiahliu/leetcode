package p25xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun takeCharacters(s: String, k: Int): Int {
            if (k == 0) {
                return 0
            }

            val pos: Array<LinkedList<Int>> = arrayOf(LinkedList(), LinkedList(), LinkedList())

            s.forEachIndexed { index, c ->
                if (pos[c - 'a'].size < k) {
                    pos[c - 'a'].add(index + 1)
                }
            }

            if (pos.any { it.size < k }) {
                return -1
            }

            var result = pos.maxOf { it.peekLast() }

            for (right in s.lastIndex downTo 0) {
                pos[s[right] - 'a'].pollLast()

                val left = pos.maxOf { it.peekLast() ?: 0 }

                result = minOf(result, left + s.length - right)
            }

            return result
        }
    }

    expect {
        Solution().takeCharacters(
            "aabaaaacaabc", 2
        )
    }
}