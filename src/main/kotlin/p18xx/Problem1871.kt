package p18xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun canReach(s: String, minJump: Int, maxJump: Int): Boolean {
            if (s[s.lastIndex] != '0') {
                return false
            }

            val reach = LinkedList<Int>()
            reach.add(0)

            s.forEachIndexed { index, c ->
                if (c == '0') {
                    var current = reach.peekFirst() ?: return false
                    while (index > current + maxJump) {
                        reach.pollFirst()
                        current = reach.peekFirst() ?: return false
                    }

                    if (index >= current + minJump) {
                        reach.add(index)
                    }
                }
            }

            return reach.peekLast() == s.lastIndex
        }
    }

    expect {
        Solution().canReach(
            "00111010", 3, 5
        )
    }
}
