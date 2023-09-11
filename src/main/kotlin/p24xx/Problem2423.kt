package p24xx

import util.expect

fun main() {
    class Solution {
        fun equalFrequency(word: String): Boolean {
            return word.groupingBy { it }.eachCount().values.let {
                val min = it.min()
                val max = it.max()
                val sum = it.sum()
                it.size == 1 || min * it.size + 1 == sum || max * (it.size - 1) == sum - 1
            }
        }
    }

    expect {
        Solution().equalFrequency(
            "abcc"
        )
    }
}