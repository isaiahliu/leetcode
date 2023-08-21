package p17xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun minimumLength(s: String): Int {
            val list = LinkedList<Pair<Int, Int>>()

            var result = s.length
            var count = 0
            var pre = s[0] - 'a'
            s.forEach {
                if (it - 'a' == pre) {
                    count++
                } else {
                    list.add(pre to count)
                    count = 1
                    pre = it - 'a'
                }
            }

            list.add(pre to count)

            while (list.size > 1 && list.peek().first == list.peekLast().first) {
                result -= list.poll().second
                result -= list.pollLast().second
            }

            if (list.size == 1 && list.peek().second > 1) {
                result = 0
            }

            return result
        }
    }

    expect {
        Solution().minimumLength(
            "ca"
        )
    }
}
