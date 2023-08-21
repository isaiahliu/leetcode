package p05xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun countArrangement(n: Int): Int {
            val stack = LinkedList<Int>()
            stack.push(1)

            var num = 2

            var result = 0

            while (true) {
                if (stack.size == n || num > n) {
                    if (stack.size == n) {
                        result++
                        stack.pop()
                    }

                    num = (stack.poll() ?: break) + 1
                } else {
                    if (num !in stack && (num % (stack.size + 1) == 0 || (stack.size + 1) % num == 0)) {
                        stack.push(num)
                        num = 1
                    } else {
                        num++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countArrangement(
            15
        )
    }
}