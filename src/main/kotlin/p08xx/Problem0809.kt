package p08xx

import util.expect

fun main() {
    class Solution {
        fun expressiveWords(s: String, words: Array<String>): Int {
            fun String.match(target: String): Boolean {
                var l = 0
                var r = 0

                //this -> hello
                //target -> heeelllloo
                var groupCount = 0
                var extend = false
                while (l < this.length || r < target.length) {
                    val left = this.getOrNull(l)
                    val right = target.getOrNull(r) ?: return false
                    when {
                        left == right -> {
                            if (extend && groupCount == 2) {
                                return false
                            }

                            extend = false
                            if (right == target.getOrNull(r - 1)) {
                                groupCount++
                            } else {
                                groupCount = 1
                            }
                            l++
                        }

                        right == target.getOrNull(r - 1) -> {
                            extend = true
                            groupCount++
                        }

                        else -> return false
                    }
                    r++
                }

                return !extend || groupCount != 2
            }

            return words.count { it.match(s) }
        }
    }

    expect {
        Solution().expressiveWords(
            "dddiiiinnssssssoooo",
            arrayOf("dinnssoo", "ddinso", "ddiinnso", "ddiinnssoo", "ddiinso", "dinsoo", "ddiinsso", "dinssoo", "dinso")
        )
    }
}