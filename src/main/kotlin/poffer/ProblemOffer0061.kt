package poffer

import util.expect

fun main() {
    class Solution {
        fun isStraight(nums: IntArray): Boolean {
            val cards = nums.filter { it > 0 }.sorted()

            var kingCount = 5 - cards.size

            var pre = cards[0]

            var index = 1
            while (index < cards.size) {
                if (cards[index] == pre + 1) {
                    pre++
                    index++
                } else if (kingCount-- > 0) {
                    pre++
                } else {
                    return false
                }
            }

            return true
        }
    }

    expect {
        Solution().isStraight(intArrayOf(0, 0, 1, 2, 5))
    }
}

