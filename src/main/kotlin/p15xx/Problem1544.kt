package p15xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun makeGood(s: String): String {
            val chars = LinkedList<Char>()

            val upper = 'A'..'Z'
            val lower = 'a'..'z'

            s.forEach {
                when {
                    chars.isEmpty() -> chars.add(it)
                    it in upper && chars.peekLast() == 'a' + (it - 'A') -> chars.pollLast()
                    it in lower && chars.peekLast() == 'A' + (it - 'a') -> chars.pollLast()
                    else -> chars.add(it)
                }
            }

            return String(chars.toCharArray())
        }
    }

    expect {
        Solution().makeGood(
            "leEeetcode"
        )
    }
}

