package p25xx

import util.expect

fun main() {
    class Solution {
        fun distMoney(money: Int, children: Int): Int {
            return when {
                money < children -> {
                    -1
                }

                money == 4 && children == 1 -> {
                    -1
                }

                children == 0 && money == 0 -> {
                    0
                }

                children == 0 -> {
                    -1
                }

                else -> {
                    distMoney(money - 8, children - 1).takeIf { it >= 0 }?.let { it + 1 } ?: 0
                }
            }
        }
    }

    expect {
        Solution().distMoney(
            12, 2
        )
    }
}