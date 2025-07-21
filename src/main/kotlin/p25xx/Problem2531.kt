package p25xx

import util.expect
import kotlin.math.absoluteValue

fun main() {
    class Solution {
        fun isItPossible(word1: String, word2: String): Boolean {
            val group1 = word1.groupingBy { it }.eachCount()
            val group2 = word2.groupingBy { it }.eachCount()

            if ((group1.size - group2.size).absoluteValue > 2) {
                return false
            }

            group1.forEach { (ch1, count1) ->
                group2.forEach { (ch2, count2) ->
                    if (ch1 == ch2) {
                        if (group1.size == group2.size) {
                            return true
                        }
                    } else {
                        var size1 = group1.size
                        if (count1 == 1) {
                            size1--
                        }
                        if (ch2 !in group1) {
                            size1++
                        }
                        var size2 = group2.size
                        if (count2 == 1) {
                            size2--
                        }
                        if (ch1 !in group2) {
                            size2++
                        }

                        if (size1 == size2) {
                            return true
                        }
                    }
                }
            }

            return false
        }
    }

    expect {
        Solution().isItPossible(
            "", ""
        )
    }
}
