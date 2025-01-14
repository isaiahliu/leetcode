package p32xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun generateKey(num1: Int, num2: Int, num3: Int): Int {
            val nums = LinkedList<Int>()
            nums += num1
            nums += num2
            nums += num3

            var result = 0
            var b = 1
            repeat(4) {
                var min = 10
                repeat(3) {
                    val num = nums.poll()

                    min = minOf(min, num % 10)

                    nums += num / 10
                }

                result += min * b
                b *= 10
            }

            return result
        }
    }

    expect {
        Solution().generateKey(
            1, 2, 3
        )
    }
}
