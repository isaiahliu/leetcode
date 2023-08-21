package p14xx

import util.expect

fun main() {
    class Solution {
        fun minNumberOfFrogs(croakOfFrogs: String): Int {
            var frogCount = 0
            var finishedCount = 0

            val counts = IntArray(4)

            croakOfFrogs.forEach {
                when (it) {
                    'c' -> {
                        if (finishedCount > 0) {
                            finishedCount--
                        } else {
                            frogCount++
                        }

                        counts[0]++
                    }

                    'r' -> {
                        if (counts[0] > 0) {
                            counts[0]--
                            counts[1]++
                        } else {
                            return -1
                        }
                    }

                    'o' -> {
                        if (counts[1] > 0) {
                            counts[1]--
                            counts[2]++
                        } else {
                            return -1
                        }
                    }

                    'a' -> {
                        if (counts[2] > 0) {
                            counts[2]--
                            counts[3]++
                        } else {
                            return -1
                        }
                    }

                    'k' -> {
                        if (counts[3] > 0) {
                            counts[3]--
                            finishedCount++
                        } else {
                            return -1
                        }
                    }
                }
            }

            return if (finishedCount == frogCount) {
                frogCount
            } else {
                -1
            }
        }
    }

    expect {
        Solution().minNumberOfFrogs(
            "abc"
        )
    }
}