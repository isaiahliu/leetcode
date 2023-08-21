package p10xx

import util.expect

fun main() {
    class Solution {
        fun longestDupSubstring(s: String): String {
            val m1 = 1000000007
            val m2 = 10000000007

            fun binarySearch(min: Int, max: Int): String? {
                if (min > max) {
                    return null
                }

                val mid = (min + max) / 2

                var result: String? = null

                var hash1 = 0L
                var maxPos1 = 1L

                var hash2 = 0L
                var maxPos2 = 1L
                repeat(mid) {
                    hash1 *= 26
                    hash1 += (s[it] - 'a')
                    hash1 %= m1
                    maxPos1 *= 26
                    maxPos1 %= m1

                    hash2 *= 26
                    hash2 += (s[it] - 'a')
                    hash2 %= m2
                    maxPos2 *= 26
                    maxPos2 %= m2
                }

                val set = hashSetOf(hash1 to hash2)

                for (i in mid until s.length) {
                    hash1 *= 26
                    hash1 -= (s[i - mid] - 'a') * maxPos1
                    hash1 += s[i] - 'a'
                    hash1 = (hash1 % m1 + m1) % m1

                    hash2 *= 26
                    hash2 -= (s[i - mid] - 'a') * maxPos2
                    hash2 += s[i] - 'a'
                    hash2 = (hash2 % m2 + m2) % m2

                    if (!set.add(hash1 to hash2)) {
                        result = s.substring(i - mid + 1..i)
                        break
                    }
                }

                set.clear()

                return if (result == null) {
                    binarySearch(min, mid - 1)
                } else {
                    binarySearch(mid + 1, max) ?: result
                }
            }

            return binarySearch(1, s.length - 1).orEmpty()
        }
    }

    expect {
        Solution().longestDupSubstring(
            "banana"
        )
    }
}