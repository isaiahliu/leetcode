package p01xx

import util.expect

fun main() {
    class Solution {
        fun maximumGap(nums: IntArray): Int {
            if (nums.size == 1) {
                return 0
            }

            val max = nums.max()
            val min = nums.min()
            val bucketSize = ((max - min) / (nums.lastIndex)).coerceAtLeast(1)

            val bucket = Array((max - min) / bucketSize + 1) {
                intArrayOf(-1, -1)
            }

            nums.forEach { num ->
                bucket[(num - min) / bucketSize].also {
                    if (it[0] == -1) {
                        it[0] = num
                        it[1] = num
                    } else {
                        it[0] = it[0].coerceAtMost(num)
                        it[1] = it[1].coerceAtLeast(num)
                    }
                }
            }

            var result = bucket[0][1] - bucket[0][0]

            var bucketMax = bucket[0][1]
            for (i in 1 until bucket.size) {
                if (bucketMax > -1) {
                    result = result.coerceAtLeast(bucket[i][0] - bucketMax)
                }

                bucketMax = bucketMax.coerceAtLeast(bucket[i][1])
            }

            return result
        }
    }

    expect {
        Solution().maximumGap(
            intArrayOf(1, 1, 1, 1)
        )
    }
}

