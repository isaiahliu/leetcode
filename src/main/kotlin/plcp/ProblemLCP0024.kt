package plcp

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun numsGame(nums: IntArray): IntArray {
            val m = 1000000007

            val queues = arrayOf(PriorityQueue<Int>(compareByDescending { it }), PriorityQueue<Int>())
            val sums = longArrayOf(0, 0)

            fun push(index: Int, num: Int) {
                queues[index].add(num)
                sums[index] += num.toLong()
            }

            fun poll(index: Int): Int {
                return queues[index].poll().also {
                    sums[index] -= it.toLong()
                }
            }

            return IntArray(nums.size) {
                val num = nums[it] + nums.lastIndex - it

                val pushIndex = when {
                    queues[1].isEmpty() -> 1
                    num > queues[1].peek() -> 1
                    else -> 0
                }

                push(pushIndex, num)

                while (queues[1].size - queues[0].size > 1) {
                    push(0, poll(1))
                }

                while (queues[0].size > queues[1].size) {
                    push(1, poll(0))
                }

                val midNum = queues[1].peek().toLong()

                ((sums[1] - sums[0] + midNum * (queues[0].size - queues[1].size)) % m).toInt()
            }
        }
    }

    expect {
        Solution().numsGame(
            intArrayOf(4, 5),
        )
    }
}
