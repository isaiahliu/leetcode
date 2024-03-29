package p02xx

fun main() {
    class Solution {
        fun countDigitOne(n: Int): Int {
            if (n == 0) {
                return 0
            }

            var sum = 0

            val str = n.toString()

            var t = 1
            repeat(str.lastIndex) {
                t *= 10
            }

            val highest = n / t

            val total = t / 10 * (str.lastIndex)

            repeat(highest) {
                sum += if (it == 1) {
                    total + t
                } else {
                    total
                }
            }

            val nextNum = n % t
            if (highest == 1) {
                sum += nextNum + 1
            }

            return sum + countDigitOne(nextNum)
        }
    }

    Solution().countDigitOne(13)
}

