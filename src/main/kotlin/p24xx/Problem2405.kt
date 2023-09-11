package p24xx

import util.expect

fun main() {
    class Solution {
        fun partitionString(s: String): Int {
            val set = (0 until 26).toMutableSet()
            var result = 0

            s.forEach {
                (it - 'a').also {
                    if (it in set) {
                        result++
                        set.clear()
                    }

                    set.add(it)
                }
            }

            return result
        }
    }

    expect {
        Solution().partitionString(
            ""
        )
    }
}