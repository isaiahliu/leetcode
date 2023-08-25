package p20xx

import util.expect

fun main() {
    class Solution {
        fun timeRequiredToBuy(tickets: IntArray, k: Int): Int {
            var index = 0
            var result = 0
            while (true) {
                if (tickets[index] > 0) {
                    result++
                    tickets[index]--
                }

                if (index == k && tickets[index] == 0) {
                    return result
                }

                index = (index + 1) % tickets.size
            }
        }
    }

    expect(1) {
        Solution().timeRequiredToBuy(
            intArrayOf(2, 3, 2), 2
        )
    }
}