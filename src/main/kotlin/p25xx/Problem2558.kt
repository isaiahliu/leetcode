package p25xx

import util.expect
import java.util.*
import kotlin.math.sqrt

fun main() {
    class Solution {
        fun pickGifts(gifts: IntArray, k: Int): Long {
            val queue = PriorityQueue<Int>(compareByDescending { it })
            var result = 0L
            gifts.forEach {
                result += it
                queue.add(it)
            }
            for (c in 0 until k) {
                val num = queue.poll()
                val newNum = sqrt(num.toDouble()).toInt()

                if (num == newNum) {
                    break
                }
                result -= num
                result += newNum

                queue.add(newNum)
            }

            return result
        }
    }

    expect {
        Solution().pickGifts(
            intArrayOf(), 1
        )
    }
}
