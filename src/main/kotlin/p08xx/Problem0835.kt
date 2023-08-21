package p08xx

import util.expect

fun main() {
    class Solution {
        fun largestOverlap(img1: Array<IntArray>, img2: Array<IntArray>): Int {
            val n = img1.size

            val imgNum1 = img1.map { it.joinToString("").toInt(2) }.toIntArray()
            val imgNum2 = img2.map { it.joinToString("").toInt(2) }.toIntArray()

            var result = 0
            fun compare(a1: IntArray, a2: IntArray) {
                repeat(n) { c ->
                    val movedArray = a1.map { it shr c }.toIntArray()

                    repeat(n) { r ->
                        var t1 = 0
                        var t2 = 0
                        for (i in r until n) {
                            t1 += Integer.bitCount(movedArray[i - r] and a2[i])
                            t2 += Integer.bitCount(movedArray[i] and a2[i - r])
                        }

                        result = result.coerceAtLeast(t1)
                        result = result.coerceAtLeast(t2)
                    }
                }
            }

            compare(imgNum1, imgNum2)
            compare(imgNum2, imgNum1)

            return result
        }
    }

    expect {
        Solution().largestOverlap(
            arrayOf(
                intArrayOf(0, 0, 1),
                intArrayOf(0, 0, 0),
                intArrayOf(0, 0, 0),
            ), arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(0, 0, 0),
                intArrayOf(1, 0, 0),
            )
        )
    }
}