package p23xx

import util.expect

fun main() {
    class Solution {
        fun longestSubsequence(s: String, k: Int): Int {

            val leadingZeroCount = IntArray(s.length)

            var count = 0
            s.forEachIndexed { index, c ->
                leadingZeroCount[index] = count

                if (c == '0') {
                    count++
                }
            }

            var result = count
            val target = k.toString(2)

            for (index in s.lastIndex downTo 0) {
                if (s[index] == '0') {
                    continue
                }

                if (leadingZeroCount[index] + target.length <= result) {
                    break
                }

                val remainSize = s.length - index
                if (remainSize < target.length) {
                    result = result.coerceAtLeast(leadingZeroCount[index] + remainSize)
                } else {
                    var maxSize = 0

                    var i1 = index

                    while (maxSize < target.length && i1 < s.length) {
                        val c = s[i1]
                        val t = target[maxSize]
                        when (t) {
                            c -> {
                                i1++
                                maxSize++
                            }

                            '0' -> {
                                i1++
                            }

                            else -> {
                                maxSize += (s.length - i1).coerceAtMost(target.length - maxSize)
                                break
                            }
                        }
                    }

                    result = result.coerceAtLeast(leadingZeroCount[index] + maxSize)
                }
            }

            return result
        }
    }

    expect {
        Solution().longestSubsequence(
            "", 5
        )
    }
}