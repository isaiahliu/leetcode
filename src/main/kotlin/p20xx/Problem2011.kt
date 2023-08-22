package p20xx

import util.expect

fun main() {
    class Solution {
        fun finalValueAfterOperations(operations: Array<String>): Int {
            var result = 0

            operations.forEach {
                when (it[1]) {
                    '+' -> {
                        result++
                    }

                    '-' -> {
                        result--
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().finalValueAfterOperations(
            arrayOf()
        )
    }
}