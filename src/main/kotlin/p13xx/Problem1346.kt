package p13xx

import util.expect

fun main() {
    class Solution {
        fun checkIfExist(arr: IntArray): Boolean {
            val set = hashSetOf<Int>()

            arr.forEach {
                if (it * 2 in set) {
                    return true
                }

                if (it % 2 == 0 && it / 2 in set) {
                    return true
                }

                set.add(it)
            }

            return false
        }
    }

    expect {
        Solution().checkIfExist(
            intArrayOf(10, 2, 5, 3)
        )
    }
}

