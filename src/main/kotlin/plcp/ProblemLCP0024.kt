package plcp

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun numsGame(nums: IntArray): IntArray {
            val m = 1000000007

            val queues = arrayOf(PriorityQueue<Int>(compareByDescending { it }), PriorityQueue<Int>())
            var sum = 0L

            fun push(index: Int, num: Int) {
                queues[index].add(num)

                if (index == 0) {
                    sum -= num
                } else {
                    sum += num
                }
            }

            fun poll(index: Int): Int {
                return queues[index].poll().also {
                    if (index == 0) {
                        sum += it
                    } else {
                        sum -= it
                    }
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

                ((sum + midNum * (queues[0].size - queues[1].size)) % m).toInt()
            }
        }
    }

    expect {
        Solution().numsGame(
            intArrayOf(3, 4, 5, 1, 6, 7),
        )
    }
}
