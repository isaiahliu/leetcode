package p09xx

import util.expect

fun main() {
    class Solution {
        fun brokenCalc(startValue: Int, target: Int): Int {
            var curr = startValue

            var result = 0

            var maxProcess = 1
            while (curr < target) {
                curr *= 2
                maxProcess *= 2
                result++
            }

            var addition = curr - target

            result += (addition / maxProcess).also {
                addition -= it * maxProcess
            }

            result += Integer.bitCount(addition)

            return result
        }
    }

    expect {
        Solution().brokenCalc(
            5, 8
        )
    }
}
