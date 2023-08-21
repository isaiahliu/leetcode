package p12xx

import util.expect

fun main() {
    class Solution {
        fun findSpecialInteger(arr: IntArray): Int {
            var pre = arr[0]
            var count = 0
            arr.forEach {
                if (pre == it) {
                    count++

                    if (count * 4 > arr.size) {
                        return it
                    }
                } else {
                    pre = it
                    count = 1
                }
            }

            return -1
        }
    }

    expect {
        Solution().findSpecialInteger(
            intArrayOf(0, 1)
        )
    }
}
