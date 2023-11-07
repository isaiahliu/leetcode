package p25xx

import util.expect

fun main() {
    class Solution {
        fun vowelStrings(words: Array<String>, left: Int, right: Int): Int {
            val vowels = setOf('a', 'e', 'i', 'o', 'u')
            return (left..right).count {
                words[it].first() in vowels && words[it].last() in vowels
            }
        }
    }

    expect {
        Solution().vowelStrings(
            arrayOf(), 1, 2
        )
    }
}

