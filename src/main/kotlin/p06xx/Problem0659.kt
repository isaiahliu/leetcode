package p06xx

import java.util.*
import util.expect

fun main() {
    class Solution {
        fun isPossible(nums: IntArray): Boolean {
            var pre = nums[0]

            val previousQueue = PriorityQueue<Int>()
            val currentQueue = PriorityQueue<Int>()

            nums.forEach { num ->
                if (num != pre) {
                    if (previousQueue.poll()?.takeIf { it < 3 } != null) {
                        return false
                    }

                    previousQueue.clear()
                    if (num != pre + 1) {
                        if (currentQueue.poll()?.takeIf { it < 3 } != null) {
                            return false
                        }
                    } else {
                        previousQueue.addAll(currentQueue)
                    }

                    currentQueue.clear()

                    pre = num
                }

                currentQueue.add((previousQueue.poll() ?: 0) + 1)
            }

            return previousQueue.poll()?.takeIf { it < 3 } == null && currentQueue.poll()?.takeIf { it < 3 } == null
        }
    }

    expect {
        Solution().isPossible(
            intArrayOf(1, 2, 3, 4, 4, 5)
        )
    }
}