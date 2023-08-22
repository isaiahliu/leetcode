package p20xx

import util.expect

fun main() {
    class Solution {
        fun construct2DArray(original: IntArray, m: Int, n: Int): Array<IntArray> {
            if (m * n != original.size) {
                return emptyArray()
            }

            var index = 0

            return Array(m) {
                IntArray(n) {
                    original[index++]
                }
            }
        }
    }

    expect {
        Solution().construct2DArray(
            intArrayOf(), 1, 2
        )
    }
}