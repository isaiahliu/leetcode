package p24xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun secondGreaterElement(nums: IntArray): IntArray {
            val stack = LinkedList<Int>()
            val found1 = PriorityQueue<Int>(compareBy { nums[it] })

            val result = IntArray(nums.size) { -1 }

            nums.forEachIndexed { index, num ->
                while (found1.isNotEmpty() && nums[found1.peek()] < num) {
                    result[found1.poll()] = num
                }

                while (stack.isNotEmpty() && nums[stack.peek()] < num) {
                    found1.offer(stack.poll())
                }

                stack.push(index)
            }

            return result
        }
    }

    expect {
        Solution().secondGreaterElement(
            intArrayOf()
        )
    }
}