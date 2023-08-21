package p11xx

import util.expect

fun main() {
    class Solution {
        fun minHeightShelves(books: Array<IntArray>, shelfWidth: Int): Int {
            val dp = IntArray(books.size) {
                Int.MAX_VALUE
            }

            for (i in dp.indices) {
                var totalThickness = 0
                var maxHeight = 0

                for (bookIndex in i downTo 0) {
                    val (t, h) = books[bookIndex]

                    totalThickness += t

                    if (totalThickness <= shelfWidth) {
                        maxHeight = maxHeight.coerceAtLeast(h)

                        dp[i] = dp[i].coerceAtMost(maxHeight + (dp.getOrNull(bookIndex - 1) ?: 0))
                    } else {
                        break
                    }
                }
            }

            return dp[dp.lastIndex]
        }
    }

    expect {
        Solution().minHeightShelves(
            arrayOf(), 3
        )

    }
}