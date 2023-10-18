package p25xx

import util.expect
import java.util.*
import kotlin.math.sign

fun main() {
    class Solution {
        fun maxKelements(nums: IntArray, k: Int): Long {
            val queue = PriorityQueue<Int>(compareByDescending { it })
            nums.forEach {
                queue.add(it)
            }

            var result = 0L

            repeat(k) {
                val max = queue.poll()

                result += max

                queue.add(max / 3 + (max % 3).sign)
            }

            return result
        }
    }

    expect {
        Solution().maxKelements(
            intArrayOf(), 1
        )
    }
}
