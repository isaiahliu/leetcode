package p21xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun recoverArray(nums: IntArray): IntArray {
            nums.sort()

            loop@ for (largerStart in 1 until nums.size) {
                if (nums[largerStart] == nums[0] || (nums[largerStart] - nums[0]) % 2 == 1) {
                    continue@loop
                }

                val k = (nums[largerStart] - nums[0]) / 2

                val result = IntArray(nums.size / 2)

                val largerQueue = LinkedList<Int>()
                var index = 0
                for (num in nums) {
                    when {
                        largerQueue.peek() == num -> {
                            largerQueue.poll()
                        }

                        index == result.size -> {
                            continue@loop
                        }

                        largerQueue.isEmpty() || largerQueue.peek() > num -> {
                            result[index++] = num + k
                            largerQueue.add(num + k * 2)
                        }

                        else -> {
                            continue@loop
                        }
                    }
                }

                return result
            }

            return intArrayOf()
        }
    }

    expect {
        Solution().recoverArray(
            intArrayOf(2, 10, 6, 4, 8, 12)
        ).toList()
    }
}