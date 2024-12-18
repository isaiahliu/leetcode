package p32xx

import util.expect

fun main() {
    class Solution {
        fun stableMountains(height: IntArray, threshold: Int): List<Int> {
            return (1 until height.size).filter { height[it - 1] > threshold }
        }
    }

    expect {
        Solution().stableMountains(
            intArrayOf(), 5
        )
    }
}
