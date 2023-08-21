package p12xx

import util.expect

fun main() {
    class Solution {
        fun minimumSwap(s1: String, s2: String): Int {
            val arr1 = s1.toCharArray()
            val arr2 = s2.toCharArray()

            var result = 0

            val BEST = 1
            val GOOD = 2
            val BAD = 3
            val WORST = 4
            val INVALID = Int.MAX_VALUE
            for (index in arr1.indices) {
                val c1 = arr1[index]
                val c2 = arr2[index]

                if (c1 == c2) {
                    continue
                }

                var type = INVALID
                var matchIndex = 0
                for (i in index + 1 until arr1.size) {
                    if (arr1[i] == c1 && arr2[i] == c2) {
                        type = BEST
                        matchIndex = i
                        break
                    } else if (type > GOOD && arr1[i] == c1) {
                        type = GOOD
                        matchIndex = i
                    } else if (type > BAD && arr2[i] == c1 && arr1[i] == c2) {
                        type = BAD
                        matchIndex = i
                    } else if (type > WORST && arr2[i] == c1) {
                        type = WORST
                        matchIndex = i
                    }
                }

                when (type) {
                    BEST, GOOD -> {
                        arr1[matchIndex] = c2
                        result++
                    }

                    BAD, WORST -> {
                        arr2[matchIndex] = arr1[matchIndex]
                        arr1[matchIndex] = c2
                        result += 2
                    }

                    else -> {
                        return -1
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minimumSwap(
            "xx", "yx"
        )
    }
}

