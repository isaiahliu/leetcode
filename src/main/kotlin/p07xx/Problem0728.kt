package p07xx

import util.expect

fun main() {
    class Solution {
        fun selfDividingNumbers(left: Int, right: Int): List<Int> {
            return (left..right).filter { num ->
                num.toString().map { it - '0' }.all {
                    it > 0 && num % it == 0
                }
            }
        }
    }

    expect {
        Solution().selfDividingNumbers(
            1, 2
        )
    }
}