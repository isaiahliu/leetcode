package p22xx

import util.expect

fun main() {
    class Solution {
        fun removeAnagrams(words: Array<String>): List<String> {
            var pre = ""

            return words.filter {
                it.toCharArray().also { it.sort() }.concatToString().let {
                    it != pre.apply { pre = it }
                }
            }
        }
    }

    expect(3) {
        Solution().removeAnagrams(
            arrayOf()
        )
    }
}