package p08xx

import util.expect

fun main() {
    class Solution {
        fun numRescueBoats(people: IntArray, limit: Int): Int {
            people.sort()

            var l = 0
            var r = people.lastIndex

            var result = 0
            while (l < r) {
                if (people[l] + people[r] <= limit) {
                    l++
                }
                r--
                result++
            }

            if (l == r) {
                result++
            }

            return result
        }
    }

    expect {
        Solution().numRescueBoats(
            intArrayOf(3, 5, 3, 4), 5
        )
    }
}