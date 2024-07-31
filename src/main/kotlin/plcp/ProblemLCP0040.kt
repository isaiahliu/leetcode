package plcp

import util.expect

fun main() {
    class Solution {
        fun maxmiumScore(cards: IntArray, cnt: Int): Int {
            cards.sortDescending()

            var sum = 0

            val part1Min = arrayOfNulls<Int>(2)
            val part2Max = arrayOfNulls<Int>(2)

            cards.forEachIndexed { index, num ->
                if (index < cnt) {
                    sum += num

                    part1Min[num % 2] = num
                } else {
                    part2Max[num % 2] = part2Max[num % 2] ?: num
                }
            }

            return sum - (minOf(part1Min[0]?.let { min -> part2Max[1]?.let { min - it } } ?: sum,
                part1Min[1]?.let { min -> part2Max[0]?.let { min - it } } ?: sum).takeIf { sum % 2 == 1 } ?: 0)
        }
    }

    expect {
        Solution().maxmiumScore(
            intArrayOf(1, 10, 1), 1
        )
    }
}
