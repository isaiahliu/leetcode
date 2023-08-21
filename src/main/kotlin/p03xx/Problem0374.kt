package p03xx

import util.expect

fun main() {
    abstract class GuessGame {
        val pick = 1702766719
        fun guess(num: Int): Int {
            return when {
                pick < num -> -1
                pick > num -> 1
                else -> 0
            }
        }

        abstract fun guessNumber(n: Int): Int
    }

    class Solution : GuessGame() {
        override fun guessNumber(n: Int): Int {
            var left = 1
            var right = n

            while (true) {
                val mid = left + (right - left) / 2

                when (guess(mid)) {
                    1 -> {
                        left = mid + 1
                    }

                    -1 -> {
                        right = mid - 1
                    }

                    0 -> {
                        return mid
                    }
                }
            }
        }
    }

    expect {
        Solution().guessNumber(2126753390)
    }
}

