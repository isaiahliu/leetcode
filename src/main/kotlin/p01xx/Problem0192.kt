package p01xx

fun main() {
    class Solution {
        fun hammingWeight(n: Int): Int {
            return Integer.bitCount(n)
        }
    }

    println(
        Solution().hammingWeight(
            -3
        )
    )
}

