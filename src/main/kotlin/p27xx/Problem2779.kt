package p27xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumBeauty(nums: IntArray, k: Int): Int {
            nums.sort()

            var result = 0

            val queue = LinkedList<Int>()
            nums.forEach {
                while (queue.isNotEmpty() && queue.peek() + k * 2 < it) {
                    queue.poll()
                }
                queue.add(it)

                result = maxOf(result, queue.size)
            }

            return result
        }
    }

    expect {
        Solution().maximumBeauty(
            intArrayOf(), 1
        )
    }
}
