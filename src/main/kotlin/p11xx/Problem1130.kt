package p11xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun mctFromLeafValues(arr: IntArray): Int {
            val stack = LinkedList<Int>()

            var index = 0
            var result = 0
            while (index < arr.size) {
                val num = arr[index]

                if (stack.isEmpty() || stack.peek() > num) {
                    stack.push(num)
                    index++
                } else {
                    val min = stack.poll()

                    if (stack.isEmpty() || stack.peek() > num) {
                        result += min * num
                        stack.push(num)

                        index++
                    } else {
                        result += stack.peek() * min
                    }
                }
            }

            while (stack.isNotEmpty()) {
                result += stack.poll() * (stack.peek() ?: break)
            }

            return result
        }
    }

    expect {
        Solution().mctFromLeafValues(
            intArrayOf(1, 2, 3)
        )

    }
}