package p15xx

import util.expect

fun main() {
    class Solution {
        fun minOperations(logs: Array<String>): Int {
            var result = 0

            logs.forEach {
                when (it) {
                    "../" -> {
                        if (result > 0) {
                            result--
                        }
                    }

                    "./" -> {
                    }

                    else -> {
                        result++
                    }
                }
            }

            return result
        }
    }

    expect {
        Solution().minOperations(
            arrayOf("")
        )
    }
}

