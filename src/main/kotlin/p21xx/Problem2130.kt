package p21xx

import util.ListNode
import util.expect
import java.util.*

fun main() {
    class Solution {
        fun pairSum(head: ListNode?): Int {
            val nums = LinkedList<Int>()

            var t = head
            while (t != null) {
                nums.add(t.`val`)
                t = t.next
            }

            var result = 0

            while (nums.isNotEmpty()) {
                result = result.coerceAtLeast(nums.poll() + nums.pollLast())
            }

            return result
        }
    }

    expect {
        Solution().pairSum(
            null
        )
    }
}