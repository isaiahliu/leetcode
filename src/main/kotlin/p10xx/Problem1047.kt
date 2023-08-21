package p10xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun removeDuplicates(s: String): String {
            val list = LinkedList<Char>()

            s.forEach {
                if (list.peekLast() == it) {
                    list.pollLast()
                } else {
                    list.add(it)
                }
            }

            return String(list.toCharArray())
        }
    }

    expect {
        Solution().removeDuplicates(
            "abbaca"
        )
    }
}