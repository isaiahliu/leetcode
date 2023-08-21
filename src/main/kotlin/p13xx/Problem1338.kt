package p13xx

import util.expect

fun main() {
    class Solution {
        fun minSetSize(arr: IntArray): Int {
            val counts = arr.toList().groupingBy { it }.eachCount().values.sortedDescending()

            var removed = 0

            var result = 0

            for (count in counts) {
                removed += count
                result++

                if (removed * 2 >= arr.size) {
                    break
                }
            }

            return result
        }
    }

    expect {
        Solution().minSetSize(
            intArrayOf()
        )
    }
}

