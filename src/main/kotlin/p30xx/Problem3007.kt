package p30xx

import util.expect
import kotlin.math.sign

fun main() {
    class Solution {
        fun findMaximumNumber(k: Long, x: Int): Long {
            var remain = k
            fun find(fixedPrefixValues: Int, maxDigit: Int): Long {
                var sum = 0L
                var value = 0L
                var digit = 0

                while (digit < maxDigit) {
                    val preCount = 1L shl (digit++)
                    val digitValue = (digit % x).sign xor 1

                    var newValue = sum
                    sum *= 2
                    sum += digitValue * preCount
                    newValue += fixedPrefixValues * preCount
                    newValue += digitValue

                    if (newValue <= remain) {
                        value = newValue
                    } else {
                        digit--
                        break
                    }
                }

                return if (digit > 0 && remain >= value) {
                    remain -= value
                    (1L shl (digit - 1)) + find(fixedPrefixValues + ((digit % x).sign xor 1), digit - 1)
                } else {
                    0L
                }
            }

            return find(0, Int.MAX_VALUE)
        }
    }

    expect(6) {
        Solution().findMaximumNumber(
            9, 1
        )
    }
    expect(50) {
        Solution().findMaximumNumber(
            19, 6
        )
    }

    expect(9) {
        Solution().findMaximumNumber(
            7, 2
        )
    }
}
