package p32xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun getFinalState(nums: IntArray, k: Int, multiplier: Int): IntArray {
            if (multiplier == 1) {
                return nums
            }

            val m = 1000000007L
            var max = 0
            val queue = PriorityQueue(compareBy<Pair<Long, Int>> { it.first }.thenBy { it.second });

            for (i in nums.indices) {
                max = maxOf(max, nums[i])
                queue.offer(nums[i].toLong() to i)
            }

            var tk = k
            while (queue.peek().first < max && tk > 0) {
                queue.poll().also { (num, index) ->
                    queue.offer(num * multiplier to index)
                }
                tk--
            }

            for (numIndex in nums.indices) {
                queue.poll().also { (x, y) ->
                    nums[y] = ((x % m) * multiplier.toBigInteger().modPow((tk / nums.size + (if (numIndex < tk % nums.size) 1 else 0)).toBigInteger(), m.toBigInteger())
                        .toLong() % m).toInt()
                }
            }

            return nums
        }
    }

    expect {
        Solution().getFinalState(
            intArrayOf(), 1, 1
        )
    }
}
