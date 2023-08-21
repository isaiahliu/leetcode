package p10xx

import util.expect

fun main() {
    class Solution {
        fun sampleStats(count: IntArray): DoubleArray {
            var sum = 0.0
            var totalCount = 0
            var mostNum = 0
            var mostCount = 0
            var midNum = 0.0

            var l = 0
            var r = count.lastIndex

            fun move() {
                while (l <= r && count[l] == 0) {
                    l++
                }

                while (r >= l && count[r] == 0) {
                    r--
                }
            }

            move()

            val min = l
            val max = r

            while (l < r) {
                val lCount = count[l]
                val rCount = count[r]

                if (lCount > mostCount) {
                    mostCount = lCount
                    mostNum = l
                }

                if (rCount > mostCount) {
                    mostCount = rCount
                    mostNum = r
                }

                if (lCount == rCount) {
                    midNum = (l + r) / 2.0
                }

                lCount.coerceAtMost(rCount).also {
                    totalCount += it * 2
                    sum += 1.0 * (l + r) * it
                    count[l] -= it
                    count[r] -= it

                }

                move()
            }

            if (count[l] > 0) {
                totalCount += count[l]
                sum += 1.0 * count[l] * l
                midNum = l.toDouble()
            }

            return doubleArrayOf(
                min.toDouble(),
                max.toDouble(),
                sum / totalCount,
                midNum,
                mostNum.toDouble()
            )
        }
    }

    expect {
        Solution().sampleStats(
            intArrayOf(
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                3510,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                6718,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                27870,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                1,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                35,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                26256,
                0,
                0,
                0,
                0,
                9586565,
                0,
                0,
                0,
                0,
                0,
                0,
                0,
                2333,
                0,
                0,
                0,
                0
            )
        ).toList()
    }
}
