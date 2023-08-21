package p12xx

import util.expect

fun main() {
    class Solution {
        fun reconstructMatrix(upper: Int, lower: Int, colsum: IntArray): List<List<Int>> {
            val array1 = IntArray(colsum.size)
            val array2 = IntArray(colsum.size)

            var count1 = 0
            colsum.forEachIndexed { index, n ->
                if (n == 2) {
                    array1[index] = 1
                    count1++

                }
            }
            if (count1 > upper) {
                return emptyList()
            }

            var count2 = 0
            colsum.forEachIndexed { index, s ->
                if (s > 0) {
                    if (count1 < upper && array1[index] == 0) {
                        array1[index] = 1
                        count1++
                    } else {
                        array2[index] = 1
                        count2++
                    }
                }
            }

            return if (count1 == upper && count2 == lower) {
                listOf(array1.toList(), array2.toList())
            } else {
                emptyList()
            }
        }
    }

    expect {
        Solution().reconstructMatrix(
            5, 5, intArrayOf()
        )
    }
}

