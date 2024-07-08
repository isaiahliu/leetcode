package p31xx

import util.expect

fun main() {
    class Solution {
        fun remove(arr: List<IntArray>, i: Int): Int {
            val n = arr.size
            return when (i) {
                arr[0][1] -> {
                    arr[n - 1][0] - arr[1][0]
                }

                arr[n - 1][1] -> {
                    arr[n - 2][0] - arr[0][0]
                }

                else -> {
                    arr[n - 1][0] - arr[0][0]
                }
            }
        }

        fun minimumDistance(points: Array<IntArray>): Int {
            val n: Int = points.size
            val sx: MutableList<IntArray> = arrayListOf()
            val sy: MutableList<IntArray> = arrayListOf()
            for (i in 0 until n) {
                val x = points[i][0]
                val y = points[i][1]
                sx.add(intArrayOf(x - y, i))
                sy.add(intArrayOf(x + y, i))
            }
            sx.sortBy { it[0] }
            sy.sortBy { it[0] }
            val maxVal1 = sx[sx.size - 1][0] - sx[0][0]
            val maxVal2 = sy[sy.size - 1][0] - sy[0][0]
            var result = Int.MAX_VALUE
            if (maxVal1 >= maxVal2) {
                val i = sx[0][1]
                val j = sx[sx.size - 1][1]
                result = minOf(result, maxOf(remove(sx, i), remove(sy, i)))
                result = minOf(result, maxOf(remove(sx, j), remove(sy, j)))
            } else {
                val i = sy[0][1]
                val j = sy[sy.size - 1][1]
                result = minOf(result, maxOf(remove(sx, i), remove(sy, i)))
                result = minOf(result, maxOf(remove(sx, j), remove(sy, j)))
            }

            return result
        }
    }

    expect {
        Solution().minimumDistance(
            arrayOf()
        )
    }
}
