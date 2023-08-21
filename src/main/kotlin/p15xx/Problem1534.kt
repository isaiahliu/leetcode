package p15xx

import util.expect

fun main() {
    class Solution {
        fun countGoodTriplets(arr: IntArray, a: Int, b: Int, c: Int): Int {
            var result = 0

            for (i in arr.indices) {
                val range1 = arr[i] - a..arr[i] + a
                val range3 = arr[i] - c..arr[i] + c

                for (j in i + 1 until arr.size) {
                    if (arr[j] !in range1) {
                        continue
                    }

                    val range2 = arr[j] - b..arr[j] + b

                    for (k in j + 1 until arr.size) {
                        if (arr[k] in range2 && arr[k] in range3) {
                            result++
                        }
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().countGoodTriplets(
            intArrayOf(), 1, 3, 4
        )
    }
}

