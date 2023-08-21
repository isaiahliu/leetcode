package p00xx

import util.expect

fun main() {
    class Solution {
        fun restoreIpAddresses(s: String): List<String> {
            val result = arrayListOf<String>()

            fun walk(previousNum: Int, index: Int, route: List<Int>) {
                if (index == s.length) {
                    if (route.size == 4) {
                        result.add(route.joinToString("."))
                    }

                    return
                }

                if (route.size == 4) {
                    return
                }

                val currentNum = previousNum + (s[index] - '0')

                if (currentNum < 256) {
                    walk(0, index + 1, route + currentNum)
                    if (currentNum > 0) {
                        walk(currentNum * 10, index + 1, route)
                    }
                }
            }

            walk(0, 0, emptyList())

            return result
        }
    }

    expect {
        Solution().restoreIpAddresses(
            ""
        )
    }
}

