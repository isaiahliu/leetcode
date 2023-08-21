package p14xx

import util.expect

fun main() {
    class Solution {
        fun maxVowels(s: String, k: Int): Int {
            val vowels = hashSetOf('a', 'e', 'i', 'o', 'u')

            var left = 0
            var right = 0

            var result = 0
            var count = 0
            while (right < s.length) {
                if (s[right++] in vowels) {
                    count++
                }

                if (right - left > k) {
                    if (s[left++] in vowels) {
                        count--
                    }
                }

                result = result.coerceAtLeast(count)
            }

            return result
        }
    }

    expect {
        Solution().maxVowels(
            "abciiidef", 4
        )

    }
}

