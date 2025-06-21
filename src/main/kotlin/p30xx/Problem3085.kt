package p30xx

import util.expect

fun main() {
    class Solution {
        fun minimumDeletions(word: String, k: Int): Int {
            val counts = word.groupingBy { it }.eachCount().values.groupingBy { it }.eachCount().entries.sortedBy { it.key }

            var result = Int.MAX_VALUE

            var left = 0
            var right = 0

            var dropCount = 0
            while (right < counts.size) {
                val (leftCount, leftSize) = counts[left]

                while (right < counts.size) {
                    val (rightCount, rightSize) = counts[right]

                    if (rightCount - leftCount > k) {
                        break
                    }

                    right++
                }

                var r = dropCount
                for (i in right until counts.size) {
                    r += (counts[i].key - leftCount - k) * counts[i].value
                }

                result = minOf(result, r)

                dropCount += leftCount * leftSize
                left++
            }

            return result
        }
    }

    expect {
        Solution().minimumDeletions(
            "aabcaba", 0
        )
    }
}
