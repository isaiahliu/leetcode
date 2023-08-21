package p11xx

import util.ListNode
import java.util.*
import util.expect

fun main() {
    class Solution {
        fun removeZeroSumSublists(head: ListNode?): ListNode? {
            val stack = LinkedList<ListNode>()

            val sums = hashMapOf<Int, Int>()

            var sum = 0
            var t = head
            while (t != null) {
                if (t.`val` != 0) {
                    sum += t.`val`

                    when (sum) {
                        0 -> {
                            sums.clear()
                            stack.clear()
                        }

                        in sums -> {
                            sum -= t.`val`
                            var poppedSum = t.`val`

                            while (poppedSum != 0) {
                                sums[sum]?.also {
                                    if (it == 1) {
                                        sums.remove(sum)
                                    } else {
                                        sums[sum] = it - 1
                                    }
                                }

                                stack.pollLast().`val`.also {
                                    poppedSum += it
                                    sum -= it
                                }
                            }

                            stack.peekLast()?.next = null
                        }

                        else -> {
                            stack.peekLast()?.next = t
                            stack.add(t)

                            sums[sum] = (sums[sum] ?: 0) + 1
                        }
                    }
                }

                t = t.next
            }

            stack.peekLast()?.next = null
            return stack.peekFirst()
        }
    }

    expect {
        Solution().removeZeroSumSublists(
            ListNode(1, ListNode(2, ListNode(3, ListNode(-3, ListNode(-2)))))
        )
    }
}