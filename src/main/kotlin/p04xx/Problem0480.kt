package p04xx

import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun medianSlidingWindow(nums: IntArray, k: Int): DoubleArray {
            if (k == 1) {
                return nums.map { it.toDouble() }.toDoubleArray()
            }

            val lowerQueue = PriorityQueue<Int>(compareByDescending { it })
            val higherQueue = PriorityQueue<Int>()

            fun adjustQueue() {
                while (lowerQueue.size > higherQueue.size + 1) {
                    higherQueue.offer(lowerQueue.poll())
                }

                while (higherQueue.size > lowerQueue.size) {
                    lowerQueue.offer(higherQueue.poll())
                }
            }

            fun mid(): Double {
                return if (k % 2 == 1) {
                    lowerQueue.peek().toDouble()
                } else {
                    (lowerQueue.peek().toDouble() + higherQueue.peek().toDouble()) / 2.0
                }
            }

            repeat(k) {
                lowerQueue.offer(nums[it])
            }

            adjustQueue()

            val result = DoubleArray(nums.size - k + 1)
            result[0] = mid()

            for (i in 1..nums.size - k) {
                val removeNum = nums[i - 1]
                val addNum = nums[i + k - 1]

                if (removeNum != addNum) {
                    if (addNum >= higherQueue.peek()) {
                        higherQueue.add(addNum)
                    } else {
                        lowerQueue.add(addNum)
                    }

                    if (removeNum >= higherQueue.peek()) {
                        higherQueue.remove(removeNum)
                    } else {
                        lowerQueue.remove(removeNum)
                    }

                    adjustQueue()
                }

                result[i] = mid()
            }
            return result
        }
    }

    measureTimeMillis {
        Solution().medianSlidingWindow(
            intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE), 2
        ).toList().also { println(it) }
    }.also { println("Time cost: ${it}ms") }
}