package p26xx

import util.expect

fun main() {
    class Solution {
        fun sumOfMultiples(n: Int): Int {
            return (1..n).fold(0) { sum, num ->
                sum + if (num % 3 == 0 || num % 5 == 0 || num % 7 == 0) num else 0
            }
        }
    }

    expect {
        Solution().sumOfMultiples(
            1
        )
    }
}