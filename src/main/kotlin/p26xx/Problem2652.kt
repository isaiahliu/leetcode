package p26xx

import util.expect

fun main() {
    class Solution {
        fun sumOfMultiples(n: Int): Int {
            return (1..n).filter {
                it % 3 == 0 || it % 5 == 0 || it % 7 == 0
            }.sum()
        }
    }

    expect {
        Solution().sumOfMultiples(
            1
        )
    }
}