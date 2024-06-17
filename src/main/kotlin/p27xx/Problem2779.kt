package p27xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun maximumBeauty(nums: IntArray, k: Int): Int {
            nums.sort()

            val queue = LinkedList<Int>()
            return nums.maxOf {
                while (queue.isNotEmpty() && queue.peek() + k * 2 < it) {
                    queue.poll()
                }
                queue.add(it)

                queue.size
            }
        }
    }

    expect {
        Solution().maximumBeauty(
            intArrayOf(), 1
        )
    }
}
