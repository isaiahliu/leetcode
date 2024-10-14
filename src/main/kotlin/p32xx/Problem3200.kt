package p32xx

import util.expect

fun main() {
    class Solution {
        fun maxHeightOfTriangle(red: Int, blue: Int): Int {
            val array = intArrayOf(0, 0)
            var index = 0
            var count = 1
            var result = 0

            while (true) {
                array[index] += count++
                index = index xor 1

                if (red >= array[0] && blue >= array[1] || red >= array[1] && blue >= array[0]) {
                    result++
                } else {
                    break
                }
            }

            return result
        }
    }

    expect {
        Solution().maxHeightOfTriangle(
            3, 21
        )
    }
}
