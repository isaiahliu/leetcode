package p07xx

import util.expect

fun main() {
    class Solution {
        fun makeLargestSpecial(s: String): String {
            var t = s

            nextStr@ while (true) {
                var startIndex = 0

                while (startIndex < t.length) {
                    var endIndex = startIndex
                    val left = intArrayOf(0, 0)

                    nextStart@ while (endIndex < t.length) {
                        left[t[endIndex] - '0']++

                        when {
                            left[0] == left[1] -> {
                                var rightEndIndex = endIndex + 1

                                val right = intArrayOf(0, 0)

                                nextEnd@ while (rightEndIndex < t.length) {
                                    right[t[rightEndIndex] - '0']++

                                    when {
                                        right[0] == right[1] -> {
                                            val leftStr = t.substring(startIndex..endIndex)
                                            val rightStr = t.substring(endIndex + 1..rightEndIndex)

                                            if (rightStr > leftStr) {
                                                t = "${t.substring(0 until startIndex)}$rightStr$leftStr" + t.substring(
                                                    rightEndIndex + 1
                                                )

                                                continue@nextStr
                                            } else {
                                                break@nextEnd
                                            }
                                        }

                                        right[0] > right[1] -> {
                                            break@nextEnd
                                        }
                                    }

                                    rightEndIndex++
                                }
                            }

                            left[0] > left[1] -> {
                                break@nextStart
                            }
                        }

                        endIndex++
                    }

                    startIndex++
                }

                break
            }

            return t
        }
    }

    expect {
        Solution().makeLargestSpecial(
            "11011000"
        )
    }
}