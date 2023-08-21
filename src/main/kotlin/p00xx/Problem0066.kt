package p00xx

import util.expect

fun main() {
    class Solution {
        fun plusOne(digits: IntArray): IntArray {
            digits[digits.lastIndex]++
            for (i in digits.lastIndex downTo 1) {
                if (digits[i] > 9) {
                    digits[i] %= 10
                    digits[i - 1]++
                } else {
                    break
                }
            }

            return if (digits[0] > 9) {
                digits[0] %= 10
                intArrayOf(1) + digits
            } else {
                digits
            }
        }
    }

    expect {
        Solution().plusOne(intArrayOf(1, 3, 1))
    }
}

