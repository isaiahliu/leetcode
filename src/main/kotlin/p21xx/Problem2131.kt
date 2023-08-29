package p21xx

import util.expect

fun main() {
    class Solution {
        fun longestPalindrome(words: Array<String>): Int {
            val sizes = Array(26) {
                Array(26) {
                    intArrayOf(0, 0)
                }
            }

            val sames = IntArray(26)
            var result = 0

            words.forEach {
                when {
                    it[0] < it[1] -> {
                        sizes[it[0] - 'a'][it[1] - 'a'][0]++
                    }

                    it[0] > it[1] -> {
                        sizes[it[1] - 'a'][it[0] - 'a'][1]++
                    }

                    else -> {
                        sames[it[0] - 'a']++
                    }
                }
            }

            for (i in sizes.indices) {
                for (j in i + 1 until sizes.size) {
                    result += sizes[i][j].min() * 4
                }
            }

            var singleUsed = false
            sames.forEach {
                result += it / 2 * 4

                if (it % 2 == 1 && !singleUsed) {
                    result += 2
                    singleUsed = true
                }
            }

            return result
        }
    }

    expect {
        Solution().longestPalindrome(
            arrayOf()
        )
    }
}