package p02xx

import util.expect

fun main() {
    class Solution {
        fun nthUglyNumber(n: Int): Int {
            val uglyNums = sortedSetOf(1L)

            var current = 1L
            repeat(n) {
                current = uglyNums.first()
                uglyNums.remove(current)

                uglyNums.add(current * 2)
                uglyNums.add(current * 3)
                uglyNums.add(current * 5)
            }

            return current.toInt()
        }
    }

    expect {
        Solution().nthUglyNumber(
            1600
        )
    }
}

