package p04xx

import util.expect

fun main() {
    class Solution {
        fun characterReplacement(s: String, k: Int): Int {
            var t = k

            var leftIndex = 0
            var leadChar = s[leftIndex]

            val counts = IntArray(26)

            var result = 0

            var rightIndex = 0
            while (rightIndex < s.length) {
                val c = s[rightIndex]

                if (c != leadChar) {
                    if (t > 0) {
                        t--
                    } else {
                        rightIndex--
                        while (s[leftIndex] == leadChar) {
                            leftIndex++
                            counts[leadChar - 'A']--
                        }

                        t -= counts[leadChar - 'A']

                        leadChar = s[leftIndex]
                        t += counts[leadChar - 'A']

                        while (t < 0) {
                            if (s[rightIndex] != leadChar) {
                                t++
                            }

                            counts[s[rightIndex] - 'A']--
                            rightIndex--
                        }

                        rightIndex++
                        continue
                    }
                }

                rightIndex++
                counts[c - 'A']++

                result = result.coerceAtLeast(rightIndex - leftIndex)
            }

            result = result.coerceAtLeast(rightIndex - leftIndex + t.coerceAtMost(leftIndex))

            return result
        }
    }

    expect {
        Solution().characterReplacement(
            "AAAAABBBBCBB",
            4
        )
    }
}


