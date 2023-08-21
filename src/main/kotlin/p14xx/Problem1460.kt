package p14xx

import util.expect

fun main() {
    class Solution {
        fun canBeEqual(target: IntArray, arr: IntArray): Boolean {
            target.sort()
            arr.sort()

            return target.indices.all { target[it] == arr[it] }
        }
    }

    expect {
        Solution().canBeEqual(
            intArrayOf(-1, -1), intArrayOf(1, 1)
        )

    }
}

