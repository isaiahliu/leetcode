package p30xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun findMaximumNumber(k: Long, x: Int): Long {
            var remain = k
            fun find(fixedValues: Int, maxDigit: Int): Long {
                if (maxDigit == 0) {
                    return 0L
                }

                var sum = ((1 % x).sign xor 1).toLong()
                var value = fixedValues.toLong() + sum
                var digit = 1

                while (digit < maxDigit) {
                    digit++
                    var newSum = sum * 2
                    var newValue = sum
                    newValue += fixedValues * (1L shl (digit - 1))
                    newValue += (digit % x).sign xor 1

                    newSum += ((digit % x).sign xor 1) * (1L shl (digit - 1))

                    if (newValue <= remain) {
                        value = newValue
                        sum = newSum
                    } else {
                        digit--
                        break
                    }
                }

                if (remain >= value) {
                    remain -= value
                    return (1L shl (digit - 1)) + find(fixedValues + ((digit % x).sign xor 1), digit - 1)
                } else {
                    return 0L
                }
            }

            return find(0, Int.MAX_VALUE)
        }
    }

    expect(50) {
        Solution().findMaximumNumber(
            19, 6
        )
    }
    expect(6) {
        Solution().findMaximumNumber(
            9, 1
        )
    }

    expect(9) {
        Solution().findMaximumNumber(
            7, 2
        )
    }
}
