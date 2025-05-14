package p29xx

import util.expect

fun main() {
    class Solution {
        fun getLongestSubsequence(words: Array<String>, groups: IntArray): List<String> {
            return buildList {
                var pre = -1
                words.forEachIndexed { index, word ->
                    if (groups[index] != pre) {
                        add(word)
                        pre = groups[index]
                    }
                }
            }
        }
    }
    expect {
        Solution().getLongestSubsequence(
            arrayOf(), intArrayOf()
        )
    }
}
