package plcp

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun magicTower(nums: IntArray): Int {
            var result = 0

            val negs = PriorityQueue<Int>(compareBy { it })
            var negSum = 0L

            var sum = 0L
            nums.forEach {
                sum += it

                if (it < 0) {
                    negs.add(it)

                    if (sum < 0) {
                        negs.poll().also {
                            sum -= it
                            negSum += it
                        }
                        result++
                    }
                }
            }

            return result.takeIf { sum + negSum >= 0 } ?: -1
        }
    }

    expect {
        Solution().magicTower(
            intArrayOf(100, 100, 100, -250, -60, -140, -50, -50, 100, 150)
        )
    }
}

