package p08xx

import kotlin.system.measureTimeMillis

fun main() {
    class Solution {
        fun primePalindrome(n: Int): Int {
            fun Int.isPrime(): Boolean {
                when {
                    this == 1 -> {
                        return false
                    }

                    this == 2 -> {
                        return true
                    }

                    this % 2 == 0 -> {
                        return false
                    }

                    else -> {
                        for (i in 3 until this) {
                            if (i * i > this) {
                                break
                            }

                            if (this % i == 0) {
                                return false
                            }
                        }

                        return true
                    }
                }
            }

            fun Int.isPalindrome(): Boolean {
                return this.toString().let { it == it.reversed() }
            }

            fun Int.nextPalindrome(): Int {
                val numStr = this.toString()
                return if (numStr == numStr.reversed()) {
                    var newNum = this

                    if (numStr.length == 1) {
                        newNum++
                    } else {
                        val addNum = ("1${"0".repeat(numStr.length / 2)}").toInt()

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
                    }${firstHalf.reversed()}".toInt()

                    if (newNum > this) {
                        newNum
                    } else {
                        newNum.nextPalindrome()
                    }
                }
            }

            var result = (n - 1).nextPalindrome()
            while (!result.isPrime()) {
                result = result.nextPalindrome()
            }

            return result
        }
    }

    measureTimeMillis {
        Solution().primePalindrome(
            4
        ).also { println(it) }

    }.also { println("Time cost: ${it}ms") }
}