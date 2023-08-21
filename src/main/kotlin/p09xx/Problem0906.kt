package p09xx

import kotlin.math.sqrt
import util.expect

fun main() {
    class Solution {
        fun superpalindromesInRange(left: String, right: String): Int {
            fun Long.isPalindrome(): Boolean {
                return this.toString().let { it == it.reversed() }
            }

            fun Long.nextPalindrome(): Long {
                val numStr = this.toString()
                return if (numStr == numStr.reversed()) {
                    var newNum = this

                    if (numStr.length == 1) {
                        newNum++
                    } else {
                        val addNum = ("1${"0".repeat(numStr.length / 2)}").toLong()

                        newNum = (this / addNum + 1) * addNum

                    }
                    if (newNum.isPalindrome()) {
                        newNum
                    } else {
                        newNum.nextPalindrome()
                    }
                } else {
                    val firstHalf = numStr.take(numStr.length / 2)

                    val newNum = "${firstHalf}${
                        numStr.drop(firstHalf.length).dropLast(firstHalf.length)
                    }${firstHalf.reversed()}".toLong()

                    if (newNum > this) {
                        newNum
                    } else {
                        newNum.nextPalindrome()
                    }
                }
            }

            var t = sqrt(left.toDouble()).toLong() - 1
            val rightLong = right.toLong()
            var result = 0
            while (true) {
                t = t.nextPalindrome()

                val pow = 1L * t * t
                if (pow > rightLong) {
                    break
                } else if (pow >= left.toLong() && pow.isPalindrome()) {
                    result++
                }
            }

            return result
        }
    }

    expect {
        Solution().superpalindromesInRange(
            "1", "2"
        )
    }
}