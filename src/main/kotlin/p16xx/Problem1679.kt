package p16xx

import util.expect

fun main() {
    class Solution {
        fun maxOperations(nums: IntArray, k: Int): Int {
            val map = hashMapOf<Int, Int>()

            var result = 0
            nums.forEach { num ->
                map[k - num]?.also {
                    if (it == 1) {
                        map.remove(k - num)
                    } else {
                        map[k - num] = it - 1
                    }

                    result++
                } ?: run {
                    map[num] = (map[num] ?: 0) + 1
                }
            }

            return result
        }
    }

    expect {
        Solution().maxOperations(
            intArrayOf(3, 1, 3, 4, 3), 6
        )
    }
}

