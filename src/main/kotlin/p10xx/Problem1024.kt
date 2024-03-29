package p10xx

import util.expect

fun main() {
    class Solution {
        fun videoStitching(clips: Array<IntArray>, time: Int): Int {
            clips.sortBy { it[0] }

            var currentMax = 0
            var index = 0

            var result = 0
            while (true) {
                result++

                var nextMax = currentMax
                while (true) {
                    clips.getOrNull(index)?.takeIf { it[0] <= currentMax }?.also {
                        nextMax = nextMax.coerceAtLeast(it[1])
                        index++
                    } ?: break
                }

                when {
                    nextMax >= time -> {
                        return result
                    }

                    nextMax > currentMax -> {
                        currentMax = nextMax
                    }

                    else -> {
                        return -1
                    }
                }
            }
        }
    }

    expect {
        Solution().videoStitching(
            arrayOf(intArrayOf(1, 2)), 3
        )
    }
}
