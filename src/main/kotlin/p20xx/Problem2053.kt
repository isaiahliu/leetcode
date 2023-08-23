package p20xx

import util.expect

fun main() {
    class Solution {
        fun kthDistinct(arr: Array<String>, k: Int): String {
            val dup = arr.groupingBy { it }.eachCount().filterValues { it > 1 }.keys

            var i = 0
            arr.forEach {
                if (it !in dup) {
                    i++

                    if (i == k) {
                        return it
                    }
                }
            }

            return ""
        }
    }

    expect {
        Solution().kthDistinct(
            arrayOf(), 1
        )
    }
}