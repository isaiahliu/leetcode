package p22xx

import util.expect
import java.util.*

fun main() {
    class Solution {
        fun minMaxGame(nums: IntArray): Int {
            val list = LinkedList<Int>()
            nums.forEach { list.add(it) }

            while (list.size > 1) {
                var min = true

                repeat(list.size / 2) {
                    val n1 = list.poll()
                    val n2 = list.poll()

                    if (min) {
                        list.add(n1.coerceAtMost(n2))
                    } else {
                        list.add(n1.coerceAtLeast(n2))
                    }

                    min = !min
                }
            }

            return list.poll()
        }
    }

    expect {
        Solution().minMaxGame(
            intArrayOf(1, 3, 5, 2, 4, 8, 2, 2)
        )
    }
}