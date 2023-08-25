package p20xx

import util.expect

fun main() {
    class Solution {
        fun countWords(words1: Array<String>, words2: Array<String>): Int {
            return words1.groupingBy { it }.eachCount().filterValues { it == 1 }.keys.intersect(
                words2.groupingBy { it }.eachCount().filterValues { it == 1 }.keys
            ).size
        }
    }

    expect {
        Solution().countWords(
            arrayOf(), arrayOf()
        )
    }
}