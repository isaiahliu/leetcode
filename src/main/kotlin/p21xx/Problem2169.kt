package p21xx

import util.expect

fun main() {
    class Solution {
        fun countOperations(num1: Int, num2: Int): Int {
            return when {
                num1 == 0 || num2 == 0 -> 0
                num1 < num2 -> {
                    num2 / num1 + countOperations(num1, num2 % num1)
                }

                else -> {
                    num1 / num2 + countOperations(num2, num1 % num2)
                }
            }
        }
    }

    expect {
        Solution().countOperations(
            1, 2
        )
    }
}