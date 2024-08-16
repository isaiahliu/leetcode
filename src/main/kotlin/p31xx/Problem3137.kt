package p31xx

import util.expect

fun main() {
    class Solution {
        fun minimumOperationsToMakeKPeriodic(word: String, k: Int): Int {
            val counts = hashMapOf<String, Int>()

            var index = 0
            while (index < word.length) {
                word.substring(index, index + k).also {
                    counts[it] = (counts[it] ?: 0) + 1
                }

                index += k
            }

            return word.length / k - counts.values.max()
        }
    }

    expect {
        Solution().minimumOperationsToMakeKPeriodic(
            "rry", 1
        )
    }
}
