package p24xx

import util.expect

fun main() {
    class Solution {
        fun productQueries(n: Int, queries: Array<IntArray>): IntArray {
            val offsets = arrayListOf<Int>()

            var t = n
            var offset = 0
            while (t > 0) {
                if (t % 2 == 1) {
                    offsets.add(offset)
                }

                offset++
                t /= 2
            }

            var sum = 0
            val sums = IntArray(offsets.size) {
                sum += offsets[it]
                sum
            }

            val m = 1000000007.toBigInteger()

            return queries.map { (from, to) ->
                2.toBigInteger().modPow((sums[to] - sums.getOrElse(from - 1) { 0 }).toBigInteger(), m).toInt()
            }.toIntArray()
        }
    }

    expect {
        Solution().productQueries(
            15, arrayOf(
                intArrayOf(0, 1),
                intArrayOf(2, 2),
                intArrayOf(0, 3),
            )
        )
    }
}