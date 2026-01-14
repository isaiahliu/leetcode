package p29xx

import util.expect

fun main() {
    class Solution {
        fun maximizeSquareHoleArea(n: Int, m: Int, hBars: IntArray, vBars: IntArray): Int {
            fun findMax(num: Int, bars: IntArray): Int {
                if (bars.isEmpty()) {
                    return 1
                }

                bars.sort()

                var max = 1
                var prev = bars[0]
                var size = 1
                bars.forEach {
                    if (it - prev != 1) {
                        size = 1
                    }
                    size++
                    prev = it
                    max = maxOf(max, size)
                }

                return max
            }

            return minOf(findMax(n, hBars), findMax(m, vBars)).let { it * it }
        }
    }

    expect {
        Solution().maximizeSquareHoleArea(
            1, 2, intArrayOf(), intArrayOf()
        )
    }
}
