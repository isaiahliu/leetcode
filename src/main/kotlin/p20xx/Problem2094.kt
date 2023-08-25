package p20xx

import util.expect

fun main() {
    class Solution {
        fun findEvenNumbers(digits: IntArray): IntArray {
            val result = sortedSetOf<Int>()
            fun dfs(num: Int, counts: Map<Int, Int>) {
                if (num >= 100) {
                    if (num % 2 == 0) {
                        result.add(num)
                    }
                } else {
                    counts.keys.forEach {
                        if (num > 0 || it > 0) {
                            dfs(num * 10 + it, counts.mapNotNull { (key, count) ->
                                when {
                                    key != it -> key to count
                                    count > 1 -> key to count - 1
                                    else -> null
                                }
                            }.toMap())
                        }
                    }
                }
            }

            dfs(0, digits.toList().groupingBy { it }.eachCount())

            return result.toIntArray()
        }
    }

    expect {
        Solution().findEvenNumbers(
            intArrayOf(2, 1, 3, 0)
        ).toList()
    }
}