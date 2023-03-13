package p04xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun circularArrayLoop(nums: IntArray): Boolean {
            val size = nums.size
            fun Int.next(clearValue: Boolean = false): Int? {
                return nums[this].takeIf { it != 0 }?.let {
                    if (clearValue) {
                        nums[this] = 0
                    }

                    ((this + it) % size + size) % size
                }
            }

            loop@ for (index in nums.indices) {
                var slow = index.next() ?: continue
                var fast = index.next()?.next() ?: continue

                while (slow != fast) {
                    slow = slow.next() ?: continue@loop
                    fast = fast.next()?.next() ?: continue@loop
                }

                var circleStart = slow.next() ?: continue

                if (slow == circleStart) {
                    continue
                }

                var sameSign = true
                while (circleStart != slow) {
                    sameSign = sameSign && nums[circleStart] * nums[slow] > 0
                    circleStart = circleStart.next(true) ?: continue
                }

                if (sameSign) {
                    return true
                }
            }
            return false
        }
    }

    measureTimeMillis {
        Solution().circularArrayLoop(intArrayOf(2, -1, 1, 2, 2)).also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}