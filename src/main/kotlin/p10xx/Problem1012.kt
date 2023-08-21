package p10xx

import util.expect

fun main() {
    class Solution {
        fun numDupDigitsAtMostN(n: Int): Int {
            if (n < 10) {
                return 0
            }

            fun Int.hasDup(): Boolean {
                return this.toString().takeIf { it.length == it.toCharArray().distinct().size } == null
            }

            val special = hashSetOf<Int>()

            var t = n
            while (t > 0) {
                special.add(t)
                t /= 10
            }

            var current = 1

            fun find(start: Int, remainingDigits: Int): Int {
                var result = 1
                repeat(remainingDigits) {
                    result *= 10
                }

                if (!start.hasDup()) {
                    var temp = 10 - start.toString().length
                    var t = 1
                    repeat(remainingDigits) {
                        t *= temp--
                    }

                    result -= t
                }

                return result
            }

            var result = if (n.hasDup()) 1 else 0

            var remainingDigits = n.toString().length - 1

            while (current < n) {
                if (current in special) {
                    current *= 10
                    remainingDigits--
                    continue
                }

                result += find(current, remainingDigits)
                current++
            }

            return result + numDupDigitsAtMostN(String(n.toString().drop(1).map { '9' }.toCharArray()).toInt())
        }
    }

    expect {
        Solution().numDupDigitsAtMostN(
            20
        )
    }
}