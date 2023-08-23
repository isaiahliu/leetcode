package p20xx

import util.expect

fun main() {
    class Solution {
        fun missingRolls(rolls: IntArray, mean: Int, n: Int): IntArray {
            var remainSum = (rolls.size + n) * mean - rolls.sum()

            return if (remainSum in n * 1..n * 6) {
                IntArray(n) {
                    (remainSum / (n - it)).also {
                        remainSum -= it
                    }
                }
            } else {
                intArrayOf()
            }
        }
    }

    expect {
        Solution().missingRolls(
            intArrayOf(3, 2, 4, 3), 4, 2
        ).toList()
    }
}